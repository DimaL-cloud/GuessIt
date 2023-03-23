package ua.dimalustyuk.GuessIt.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.dimalustyuk.GuessIt.Topic;
import ua.dimalustyuk.GuessIt.service.SceneService;
import ua.dimalustyuk.GuessIt.service.WordService;

@Component
public class TopicChoosingController {
    private final SceneService sceneService;
    private final WordService wordService;

    @Autowired
    public TopicChoosingController(SceneService sceneService, WordService wordService) {
        this.sceneService = sceneService;
        this.wordService = wordService;
    }

    @FXML
    private ToggleGroup topic;

    @FXML
    private Button backButton;

    @FXML
    private Button wordPickerButton;

    @FXML
    void initialize() {

    }

    @FXML
    void pickWord() {
        if (topic.getSelectedToggle() != null) {
            wordService.setTopic(Topic.valueOf(((RadioButton) topic.getSelectedToggle()).getId()));
            sceneService.openGameScene(wordPickerButton);
        }
    }

    @FXML
    void back() {
        sceneService.openStartMenuView(backButton);
    }

}
