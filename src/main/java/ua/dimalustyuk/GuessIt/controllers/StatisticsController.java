package ua.dimalustyuk.GuessIt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;
import ua.dimalustyuk.GuessIt.services.SceneService;
import ua.dimalustyuk.GuessIt.services.StatisticsService;

@Component
public class StatisticsController {

    private final SceneService sceneService;
    private final StatisticsService statisticsService;

    @FXML
    private Label gamesAmountLabel;

    @FXML
    private Label lossesAmountLabel;

    @FXML
    private Label winsAmountLabel;

    @FXML
    private Button backButton;

    public StatisticsController(SceneService sceneService, StatisticsService statisticsService) {
        this.sceneService = sceneService;
        this.statisticsService = statisticsService;
    }

    @FXML
    void initialize() {
        int totalGames = statisticsService.getTotalGames();
        int wins = statisticsService.getWins();
        int losses = statisticsService.getLosses();

        double winsPercent = (double) wins / totalGames * 100;
        double lossesPercent = (double) losses / totalGames * 100;

        String winsPercentRepresentation = "";
        String lossesPercentRepresentation = "";

        if (totalGames != 0) {
            winsPercentRepresentation = "(" + String.format("%.2f", winsPercent) + "%)";
            lossesPercentRepresentation = "(" + String.format("%.2f", lossesPercent) + "%)";
        }

        gamesAmountLabel.setText(String.valueOf(totalGames));
        winsAmountLabel.setText(wins + " " + winsPercentRepresentation);
        lossesAmountLabel.setText(losses + " " + lossesPercentRepresentation);
    }

    @FXML
    void back() {
        sceneService.openStartMenuScene(backButton);
    }
}
