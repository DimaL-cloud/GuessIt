package ua.dimalustyuk.GuessIt.service.impl;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.service.MusicService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class MusicServiceImpl implements MusicService {

    private final List<Media> playlist = getPlaylist();

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

    @Override
    public void play() {
        isPlaying = true;

        players.get(currentSongIndex).play();
    }

    @Override
    public void pause() {
        isPlaying = false;

        players.get(currentSongIndex).pause();
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    private List<Media> getPlaylist() {
        List<Media> playlist = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getResourceAsStream("/assets.music/musicNames.txt")))
        )) {
            String line;
            while ((line = br.readLine()) != null) {
                String musicFilePath = "/assets.music/files/" + line + ".mp3";

                playlist.add(new Media(Objects.requireNonNull(getClass().getResource(musicFilePath)).toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playlist;
    }
}
