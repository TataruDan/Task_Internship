package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Clasa pentru contorizarea cuvintelor într-un text.
 */
public class WordCounter {

    /**
     * Metoda pentru contorizarea cuvintelor dintr-un text.
     *
     * @param content textul care trebuie analizat
     * @param top numărul maxim de cuvinte frecvente care trebuie returnate
     * @return o hartă care asociază fiecare cuvânt cu numărul de apariții
     */
    public Map<String, Integer> countWords(String content, int top) {
        String[] words = content.split("\\W+");
        Map<String, Integer> wordCounts = new HashMap<>();

        // Parcurgem cuvintele și le contorizăm
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        // Sortăm cuvintele după numărul de apariții și limităm la numărul specificat de top
        return wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(top)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
