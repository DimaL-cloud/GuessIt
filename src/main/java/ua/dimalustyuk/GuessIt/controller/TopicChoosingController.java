package ua.dimalustyuk.GuessIt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.dimalustyuk.GuessIt.service.SceneService;

@Component
public class TopicChoosingController {
    private final SceneService sceneService;

    @Autowired
    public TopicChoosingController(SceneService sceneService) {
        this.sceneService = sceneService;
    }

    @FXML
    private ToggleGroup topic;

    @FXML
    private Button backButton;

    @FXML
    private Button programmingButton; // TODO change name

    @FXML
    void initialize() {

    }

    @FXML
    void startGame(ActionEvent event) {
        if (topic.getSelectedToggle() != null) {
            sceneService.openGameScene(programmingButton);
        }
    }

    @FXML
    void back(ActionEvent event) {
        sceneService.openStartMenuView(backButton);
    }

}
