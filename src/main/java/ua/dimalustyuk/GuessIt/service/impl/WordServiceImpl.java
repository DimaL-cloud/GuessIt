package ua.dimalustyuk.GuessIt.service.impl;

import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.Topic;
import ua.dimalustyuk.GuessIt.service.WordService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
        Properties wordsProperties = new Properties();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/word_topics/" + topic + "Words.properties")), StandardCharsets.UTF_8))) {
            wordsProperties.load(reader);

            List<String> terms = new ArrayList<>(wordsProperties.stringPropertyNames());

            word = terms.get(new Random().nextInt(terms.size()));
            description = wordsProperties.getProperty(word);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return word;
    }

    @Override
    public String getWordDescription() {
        return description;
    }
}
