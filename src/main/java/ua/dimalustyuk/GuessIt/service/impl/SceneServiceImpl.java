package ua.dimalustyuk.GuessIt.service.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.service.SceneService;

import java.io.IOException;

@Service
public class SceneServiceImpl implements SceneService {

    @Value("${view.topicChoosingView.path}")
    private String topicChoosingViewPath;

    @Value("${view.startMenuView.path}")
    private String startMenuViewPath;

    @Value("${view.gameView.path}")
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
