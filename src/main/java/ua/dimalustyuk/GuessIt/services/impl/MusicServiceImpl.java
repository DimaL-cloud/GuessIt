package ua.dimalustyuk.GuessIt.services.impl;

import javafx.scene.media.MediaPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.config.MusicPlayerListConfig;
import ua.dimalustyuk.GuessIt.services.MusicService;

import java.util.*;

@Service
public class MusicServiceImpl implements MusicService {

    private final List<MediaPlayer> mediaPlayerList;

    private boolean isPlaying = true;

    @Autowired
    public MusicServiceImpl(List<MediaPlayer> mediaPlayerList) {
        this.mediaPlayerList = mediaPlayerList;
    }

    @Override
    public void play() {
        int currentMusicIndex = MusicPlayerListConfig.getCurrentMusicIndex();

        mediaPlayerList.get(currentMusicIndex).play();

        isPlaying = true;
    }

    @Override
    public void pause() {
        int currentMusicIndex = MusicPlayerListConfig.getCurrentMusicIndex();

        mediaPlayerList.get(currentMusicIndex).pause();

        isPlaying = false;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }
}
