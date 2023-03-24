package ua.dimalustyuk.GuessIt;

public enum Topic {
    OOP("word_topics/OOPWords.properties"),
    ADS("ADSWords.properties"),
    JAVA("JavaWords.properties"),
    DB("DBWords.properties");

    final String fileName;

    Topic(String fileName) {
        this.fileName = fileName;
    }
}
