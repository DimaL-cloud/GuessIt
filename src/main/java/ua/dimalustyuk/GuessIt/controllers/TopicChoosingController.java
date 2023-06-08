package ua.dimalustyuk.GuessIt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.dimalustyuk.GuessIt.entities.enums.Topic;
import ua.dimalustyuk.GuessIt.services.SceneService;
import ua.dimalustyuk.GuessIt.services.WordService;

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
    void pickWord() {
        if (topic.getSelectedToggle() != null) {
            Topic selectedTopic = Topic.valueOf(((RadioButton) topic.getSelectedToggle()).getId());

            wordService.setTopic(selectedTopic);
            wordService.pickRandomWord(selectedTopic);
            sceneService.openGameScene(wordPickerButton);
        }
    }

    @FXML
    void back() {
        sceneService.openStartMenuScene(backButton);
    }
}
