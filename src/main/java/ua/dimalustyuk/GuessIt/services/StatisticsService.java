package ua.dimalustyuk.GuessIt.services;

public interface StatisticsService {
    void incrementWins();

    void incrementLosses();

    int getTotalGames();

    int getWins();

    int getLosses();
}
