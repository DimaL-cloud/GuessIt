package ua.dimalustyuk.GuessIt.config;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Configuration
public class MusicPlayerListConfig {

    @Value("${music-names-file-path}")
    private String musicNamesFilePath;

    @Value("${music-files-path}")
    private String musicFilesPath;

    private static int CURRENT_MUSIC_INDEX = 0;

    @Bean
    public List<MediaPlayer> musicPlayerList() {
        List<MediaPlayer> players = new ArrayList<>();
        List<Media> mediaList = mediaList();

        mediaList.forEach(media -> players.add(new MediaPlayer(media)));

        players.forEach(player -> player.setOnEndOfMedia(() -> {
            player.stop();

            CURRENT_MUSIC_INDEX++;

            if (CURRENT_MUSIC_INDEX == mediaList.size()) {
                CURRENT_MUSIC_INDEX = 0;
            }

            players.get(CURRENT_MUSIC_INDEX).play();
        }));

        CURRENT_MUSIC_INDEX = 0;

        players.get(0).setAutoPlay(true);

        return players;
    }

    public List<Media> mediaList() {
        List<Media> mediaList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getResourceAsStream(musicNamesFilePath)))
        )) {
            String line;
            while ((line = br.readLine()) != null) {
                String musicFilePath = musicFilesPath + line + ".mp3";

                mediaList.add(new Media(Objects.requireNonNull(getClass().getResource(musicFilePath)).toString()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read music names file");
        }

        Collections.shuffle(mediaList);

        return mediaList;
    }

    public static int getCurrentMusicIndex() {
        return CURRENT_MUSIC_INDEX;
    }
}
