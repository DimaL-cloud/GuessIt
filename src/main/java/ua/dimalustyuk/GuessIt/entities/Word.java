package ua.dimalustyuk.GuessIt.entities;

public class Word {
    private final String term;
    private final String description;

    public Word(String term, String description) {
        this.term = term;
        this.description = description;
    }

    public String getTerm() {
        return term;
    }

    public String getDescription() {
        return description;
    }
}
