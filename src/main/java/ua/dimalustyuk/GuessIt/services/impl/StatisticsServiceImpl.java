package ua.dimalustyuk.GuessIt.services.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ua.dimalustyuk.GuessIt.entities.enums.Topic;
import ua.dimalustyuk.GuessIt.services.StatisticsService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private int wins;
    private int losses;

    @Value("classpath:data/statistics.txt")
    private Resource statisticsFile;

    @Override
    public void incrementWins() {
        wins++;
        saveStatistics();
    }

    @Override
    public void incrementLosses() {
        losses++;
        saveStatistics();
    }

    @Override
    public int getTotalGames() {
        return wins + losses;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public int getLosses() {
        return losses;
    }

    @PostConstruct
    private void loadStatistics() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(statisticsFile.getInputStream(), StandardCharsets.UTF_8))) {
            Properties properties = new Properties();
            properties.load(reader);

            wins = Integer.parseInt(properties.getProperty("wins", "0"));
            losses = Integer.parseInt(properties.getProperty("losses", "0"));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    private void saveStatistics() {
        try (OutputStream outputStream = new FileOutputStream(statisticsFile.getFile())) {
            Properties properties = new Properties();

            properties.setProperty("wins", String.valueOf(wins));
            properties.setProperty("losses", String.valueOf(losses));

            properties.store(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
