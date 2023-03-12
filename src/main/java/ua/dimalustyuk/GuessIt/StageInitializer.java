package ua.dimalustyuk.GuessIt;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ua.dimalustyuk.GuessIt.service.MusicService;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<JavaFXApplication.StageReadyEvent> {
    @Value("${view.startMenuView.path}")
    private Resource resource;

    private final ApplicationContext applicationContext;

    private final MusicService musicService;

    @Autowired
    public StageInitializer(ApplicationContext applicationContext, MusicService musicService) {
        this.applicationContext = applicationContext;
        this.musicService = musicService;
    }

    @Override
    public void onApplicationEvent(JavaFXApplication.StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);

            Parent parent = fxmlLoader.load();

            Stage stage = event.getStage();

            Scene scene = new Scene(parent, 720, 480);

            stage.setMinWidth(720);
            stage.setMinHeight(500);

            stage.setTitle("GuessIt");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load resource", e);
        }
    }
}
