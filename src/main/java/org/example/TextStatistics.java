package org.example;

public class TextStatistics {
    int wordCount;
    int sentenceCount;

    public TextStatistics(String content) {
        this.wordCount = countWords(content);
        this.sentenceCount = countSentences(content);
    }

    private int countWords(String content) {
        if (content.isEmpty()) {
            return 0;
        }
        return content.split("\\s+").length;  // Corectare: de folosit \\s+ pentru a număra corect cuvintele, inclusiv cele cu apostrofuri
    }

    private int countSentences(String content) {
        if (content.isEmpty()) {
            return 0;
        }
        return content.split("[.!?]").length;
    }

    @Override
    public String toString() {
        return "+---------------------+-----+\n" +
                "| Number of words:    | " + wordCount + " |\n" +
                "+---------------------+-----+\n" +
                "| Number of sentences:| " + sentenceCount + " |\n" +
                "+---------------------+-----+";
    }
}
