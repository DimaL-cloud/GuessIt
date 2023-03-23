package ua.dimalustyuk.GuessIt.service.impl;

import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.Topic;
import ua.dimalustyuk.GuessIt.service.WordService;

import java.io.FileInputStream;
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

        try (InputStreamReader isr = new InputStreamReader(
                new FileInputStream("src/main/resources/word_topics/" + topic + "Words.properties"), StandardCharsets.UTF_8)
        ) {
            wordsProperties.load(isr);

            List<String> terms = new ArrayList<>(wordsProperties.stringPropertyNames());

            word = terms.get(new Random().nextInt(terms.size()));
            description = wordsProperties.getProperty(word);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return word;
    }

    @Override
    public String getWordDescription() {
        return description;
    }
}
