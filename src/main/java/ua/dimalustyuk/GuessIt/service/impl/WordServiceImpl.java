package ua.dimalustyuk.GuessIt.service.impl;

import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.Topic;
import ua.dimalustyuk.GuessIt.service.WordService;

@Service
public class WordServiceImpl implements WordService {
    private Topic topic = Topic.OOP;

    private String word = "Поліморфізм";
    private String description = "Можливіть об'єкта примайти багато різних форм";

    @Override
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public String getWordDescription() {
        return description;
    }
}
