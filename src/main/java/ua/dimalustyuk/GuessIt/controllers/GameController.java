package ua.dimalustyuk.GuessIt.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.dimalustyuk.GuessIt.services.SceneService;
import ua.dimalustyuk.GuessIt.services.StatisticsService;
import ua.dimalustyuk.GuessIt.services.WordService;

@Component
@Scope(value = "prototype")
public class GameController {

    private final SceneService sceneService;
    private final WordService wordService;
    private final StatisticsService statisticsService;

    @FXML
    private Label wordLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label attemptsLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private GridPane keysPane;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    private String actualWord;
    private final StringBuilder hiddenWord = new StringBuilder();

    private int lettersLeft;
    private int attemptsLeft;

    private Timeline timer;
    private int timeSeconds = 90; // 1 minute and 30 seconds

    @Autowired
    public GameController(SceneService sceneService,
                          WordService wordService,
                          StatisticsService statisticsService) {
        this.sceneService = sceneService;
        this.wordService = wordService;
        this.statisticsService = statisticsService;
    }

    @FXML
    void initialize() {
        initializeWord();
        initializeKeyboard();
        initializeAttempts();
        initializeTimer();
    }

    private void initializeWord() {
        actualWord = wordService.getTerm();
        actualWord = actualWord.replace("_", " ");

        for (int i = 0; i < actualWord.length(); i++) {
            char currentSymbol = actualWord.charAt(i);
            char symbolRepresentation = getCorrectSymbolRepresentation(currentSymbol);

            hiddenWord.append(symbolRepresentation);

            if (symbolRepresentation == '_') {
                lettersLeft++;
            }
        }

        wordLabel.setText(hiddenWord.toString());
        descriptionLabel.setText(wordService.getDescription());
    }

    private char getCorrectSymbolRepresentation(char symbol) {
        if (Character.isLetter(symbol)) {
            return '_';
        } else {
            return symbol;
        }
    }

    private void initializeKeyboard() {
        for (Node node : keysPane.getChildren()) {
            if (node instanceof ToggleButton key) {
                char letter = key.getText().charAt(0);

                key.setOnAction(actionEvent -> {
                    boolean isLetterCorrect = false;

                    for (int i = 0; i < actualWord.length(); i++) {
                        if (actualWord.toUpperCase().charAt(i) == letter) {
                            isLetterCorrect = true;
                            hiddenWord.setCharAt(i, actualWord.charAt(i));
                            lettersLeft--;
                        }
                    }

                    key.setDisable(true);

                    if (isWin()) {
                        handleWin();
                    }

                    if (!isLetterCorrect) {
                        attemptsLeft--;
                        attemptsLabel.setText(String.valueOf(attemptsLeft));
                    }

                    wordLabel.setText(hiddenWord.toString());

                    if (attemptsLeft == 0) {
                        handleLoss();
                    }
                });
            }
        }
    }

    private void handleWin() {
        wordLabel.setTextFill(Color.color(0, 0.896, 0));
        nextButton.setDisable(false);
        disableKeyboard();
        statisticsService.incrementWins();
    }

    private void handleLoss() {
        wordLabel.setTextFill(Color.color(1, 1, 0));
        wordLabel.setText(String.valueOf(actualWord));
        nextButton.setDisable(false);
        disableKeyboard();
        timer.stop();
        statisticsService.incrementLosses();
    }

    private void disableKeyboard() {
        keysPane.setDisable(true);
    }

    private boolean isWin() {
        return lettersLeft == 0;
    }

    private void initializeAttempts() {
        attemptsLeft = 3;
        attemptsLabel.setText(String.valueOf(attemptsLeft));
    }

    private void initializeTimer() {
        timerLabel.setText(formatTime(timeSeconds));

        timer = new Timeline(new KeyFrame(Duration.seconds(1), this::updateTimer));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void updateTimer(ActionEvent event) {
        timeSeconds--;
        timerLabel.setText(formatTime(timeSeconds));

        if (timeSeconds <= 0) {
            timer.stop();
            handleTimeout();
        } else if (timeSeconds == 60) {
            timerLabel.setTextFill(Color.YELLOW);
        } else if (timeSeconds == 30) {
            timerLabel.setTextFill(Color.RED);
        }
    }

    private void handleTimeout() {
        wordLabel.setTextFill(Color.color(1, 1, 0));
        handleLoss();
    }

    private String formatTime(int timeSeconds) {
        int minutes = timeSeconds / 60;
        int seconds = timeSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    @FXML
    void back() {
        sceneService.openTopicChoosingScene(backButton);
    }

    @FXML
    void next() {
        wordService.pickRandomWord(wordService.getTopic());
        sceneService.openGameScene(nextButton);
    }
}