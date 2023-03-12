package ua.dimalustyuk.GuessIt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.dimalustyuk.GuessIt.service.impl.MusicServiceImpl;
import ua.dimalustyuk.GuessIt.service.SceneService;

@Component
public class StartMenuController {
    private final MusicServiceImpl musicService;
    private final SceneService sceneService;

    @Autowired
    public StartMenuController(MusicServiceImpl musicService, SceneService sceneService) {
        this.musicService = musicService;
        this.sceneService = sceneService;
    }

    @FXML
    private Button startGameButton;

    @FXML
    private ToggleButton musicButton;

    @FXML
    void initialize() {
        musicButton.setSelected(musicService.isPlaying());
    }

    @FXML
    void startGame() { // TODO change name
        sceneService.openTopicChoosingScene(startGameButton);
    }

    @FXML
    void switchMusicState() {
        if (musicButton.isSelected()) {
            musicService.play();
        } else musicService.pause();
    }
}
