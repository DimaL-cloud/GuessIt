package ua.dimalustyuk.GuessIt.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.dimalustyuk.GuessIt.service.SceneService;
import ua.dimalustyuk.GuessIt.service.WordService;

@Component
public class GameController {

    private final SceneService sceneService;
    private final WordService wordService;

    @FXML
    private Label description;

    @FXML
    private Label word;

    @FXML
    private GridPane keysPane;

    @FXML
    private Button backButton;

    private final StringBuilder hiddenWord = new StringBuilder();

    private int lettersLeft;

    @Autowired
    public GameController(SceneService sceneService, WordService wordService) {
        this.sceneService = sceneService;
        this.wordService = wordService;
    }

    @FXML
    void initialize() {
        String actualWord = wordService.getWord();
        lettersLeft = actualWord.length();

        hiddenWord.append("_".repeat(actualWord.length()));

        word.setText(hiddenWord.toString());
        description.setText(wordService.getWordDescription());

        for (Node node : keysPane.getChildren()) {
            if (node instanceof ToggleButton key) {
                char letter = key.getText().charAt(0);

                key.setOnAction(actionEvent -> {

                    for (int i = 0; i < actualWord.length(); i++) {
                        if (actualWord.toUpperCase().charAt(i) == letter) {
                            hiddenWord.setCharAt(i, actualWord.charAt(i));
                            lettersLeft--;
                        }
                    }

                    key.setDisable(true);

                    if (lettersLeft == 0) {
                        word.setTextFill(Color.color(0, 0.896, 0));
                    }

                    word.setText(hiddenWord.toString());
                });
            }
        }
    }

    @FXML
    void back() {
        sceneService.openTopicChoosingScene(backButton);
        hiddenWord.setLength(0);
    }
}
