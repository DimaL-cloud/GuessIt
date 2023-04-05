package ua.dimalustyuk.GuessIt.service.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.service.SceneService;

import java.io.IOException;

@Service
public class SceneServiceImpl implements SceneService {

    @Value("${topic-choosing-view-path}")
    private String topicChoosingViewPath;

    @Value("${start-menu-view-path}")
    private String startMenuViewPath;

    @Value("${game-view-path}")
    private String gameViewPath;

    private final ApplicationContext applicationContext;

    public SceneServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void openStartMenuView(Node node) {
        openNewScene(node, startMenuViewPath);
    }

    @Override
    public void openTopicChoosingScene(Node node) {
        openNewScene(node, topicChoosingViewPath);
    }

    @Override
    public void openGameScene(Node node) {
        openNewScene(node, gameViewPath);
    }

    @Override
    public void openNewScene(Node node, String viewPath) {
        Scene scene = node.getScene();

        Stage window = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
        loader.setControllerFactory(applicationContext::getBean);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene.setRoot(loader.getRoot());
        window.setScene(scene);
    }
}
