package ua.dimalustyuk.GuessIt.service;

import javafx.scene.Node;

public interface SceneService {
    void openStartMenuView(Node node);

    void openTopicChoosingScene(Node node);

    void openGameScene(Node node);

    void openNewScene(Node node, String viewPath);
}
