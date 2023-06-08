package ua.dimalustyuk.GuessIt.services;

import ua.dimalustyuk.GuessIt.entities.enums.Topic;

public interface WordService {
    void pickRandomWord(Topic topic);

    String getDescription();

    String getTerm();

    Topic getTopic();

    void setTopic(Topic topic);
}
