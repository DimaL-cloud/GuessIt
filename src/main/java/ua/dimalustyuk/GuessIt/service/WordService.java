package ua.dimalustyuk.GuessIt.service;

import ua.dimalustyuk.GuessIt.Topic;

public interface WordService {
    void setTopic(Topic topic);

    String getWordDescription();

    String getWord();
}
