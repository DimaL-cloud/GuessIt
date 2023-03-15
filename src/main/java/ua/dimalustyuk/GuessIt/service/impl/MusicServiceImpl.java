package ua.dimalustyuk.GuessIt.service.impl;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.service.MusicService;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    private final List<Media> playlist = Arrays.asList(
            new Media(new File("src/main/resources/assets.music/ChillDay.mp3").toURI().toString()),
            new Media(new File("src/main/resources/assets.music/BetterDays.mp3").toURI().toString()),
            new Media(new File("src/main/resources/assets.music/You-cocabona.mp3").toURI().toString()),
            new Media(new File("src/main/resources/assets.music/AloneTime.mp3").toURI().toString()),
            new Media(new File("src/main/resources/assets.music/silent-wood.mp3").toURI().toString())
    );

    private int currentSongIndex = 0;

    private final List<MediaPlayer> players = new ArrayList<>();

    private boolean isPlaying = true;

    public MusicServiceImpl() {
        Collections.shuffle(playlist);

        playlist.forEach(media -> players.add(new MediaPlayer(media)));

        players.forEach(player -> player.setOnEndOfMedia(() -> {
            player.stop();

            currentSongIndex++;

            if (currentSongIndex == playlist.size()) {
                currentSongIndex = 0;
            }

            players.get(currentSongIndex).play();
        }));

        currentSongIndex = 0;

        players.get(0).setAutoPlay(true);
    }

    public void play() {
        isPlaying = true;

        players.get(currentSongIndex).play();
    }

    public void pause() {
        isPlaying = false;

        players.get(currentSongIndex).pause();
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
