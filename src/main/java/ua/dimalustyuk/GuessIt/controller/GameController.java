package ua.dimalustyuk.GuessIt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.dimalustyuk.GuessIt.service.SceneService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class GameController {

    private final SceneService sceneService;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    public GameController(SceneService sceneService) {
        this.sceneService = sceneService;
    }

    @FXML
    void back(ActionEvent event) {
        sceneService.openTopicChoosingScene(backButton);
    }

}
