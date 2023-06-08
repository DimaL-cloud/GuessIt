package ua.dimalustyuk.GuessIt.services.impl;

import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.entities.Word;
import ua.dimalustyuk.GuessIt.entities.enums.Topic;
import ua.dimalustyuk.GuessIt.services.WordService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class WordServiceImpl implements WordService {

    private Topic topic;
    private Word word;

    @Override
    public void pickRandomWord(Topic topic) {
        Properties wordsProperties = new Properties();

        try (BufferedReader reader = getWordReader(topic)) {

            wordsProperties.load(reader);

            List<String> terms = new ArrayList<>(wordsProperties.stringPropertyNames());

            String term = terms.get(new Random().nextInt(terms.size()));
            String description = wordsProperties.getProperty(term);

            word = new Word(term, description);

        } catch (IOException e) {
            throw new RuntimeException("Unable to pick word");
        }
    }

    @Override
    public String getTerm() {
        return word.getTerm();
    }

    @Override
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public Topic getTopic() {
        return topic;
    }

    @Override
    public String getDescription() {
        return word.getDescription();
    }

    private BufferedReader getWordReader(Topic topic) {
        return new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/word_topics/" + topic + ".txt"
                )), StandardCharsets.UTF_8)
        );
    }
}
