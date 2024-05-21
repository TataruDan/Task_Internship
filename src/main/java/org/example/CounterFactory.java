package org.example;

/**
 * Fabrica pentru crearea obiectelor WordCounter și PhraseCounter.
 */
public class CounterFactory {
    public WordCounter createWordCounter() {
        return new WordCounter();
    }

    public PhraseCounter createPhraseCounter(int phraseSize) {
        return new PhraseCounter(phraseSize);
    }
}