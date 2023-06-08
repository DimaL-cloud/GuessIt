package ua.dimalustyuk.GuessIt.services;

import javafx.scene.Node;

public interface SceneService {
    void openStartMenuScene(Node node);

    void openTopicChoosingScene(Node node);

    void openGameScene(Node node);

    void openStatisticsScene(Node node);
}
