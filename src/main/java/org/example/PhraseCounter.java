package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Clasa pentru contorizarea frazelor de o anumită dimensiune (phraseSize) într-un text (pe care il extragem din fișier).
 */
public class PhraseCounter {
    private int phraseSize;

    /**
     * Constructorul clasei PhraseCounter.
     *
     * @param phraseSize dimensiunea frazei (numărul de cuvinte dintr-o frază)
     */
    public PhraseCounter(int phraseSize) {
        this.phraseSize = phraseSize;
    }

    /**
     * Metoda pentru contorizarea frazelor dintr-un text.
     *
     * @param content textul care trebuie analizat
     * @param top numărul maxim de fraze frecvente care trebuie returnate
     * @return o hartă care asociază fiecare frază cu numărul de apariții
     */
    public Map<String, Integer> countPhrases(String content, int top) {
        String[] words = content.split("\\W+");
        Map<String, Integer> phraseCounts = new HashMap<>();

        // Parcurgem cuvintele și construim frazele de dimensiunea specificată
        for (int i = 0; i <= words.length - phraseSize; i++) {
            StringBuilder phrase = new StringBuilder();
            for (int j = 0; j < phraseSize; j++) {
                phrase.append(words[i + j]).append(" ");
            }
            String phraseStr = phrase.toString().trim();
            phraseCounts.put(phraseStr, phraseCounts.getOrDefault(phraseStr, 0) + 1);
        }

        // Sortăm frazele după numărul de apariții și limităm la numărul specificat de top
        return phraseCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(top)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
