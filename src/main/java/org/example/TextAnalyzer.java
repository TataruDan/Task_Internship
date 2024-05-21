package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Clasa TextAnalyzer este responsabilă pentru citirea fișierului text,
 * calcularea statisticilor textului și afișarea celor mai frecvente cuvinte sau fraze din cadrul fișierului pe care
 * utilizatorul introduce.
 */
public class TextAnalyzer {
    private String filePath; // Calea către fișierul text
    private int top; // Numărul maxim de cuvinte sau fraze cele mai frecvente care trebuie afișate
    private int phraseSize; // Dimensiunea frazei (numărul de cuvinte dintr-o frază)

    /**
     * Constructorul clasei TextAnalyzer
     *
     * @param filePath Calea către fișierul text
     * @param top Numărul maxim de cuvinte sau fraze cele mai frecvente | Ver.cons.
     * @param phraseSize Dimensiunea frazei (numărul de cuvinte dintr-o frază)
     */
    public TextAnalyzer(String filePath, int top, int phraseSize) {
        this.filePath = filePath;
        this.top = top;
        this.phraseSize = phraseSize;
    }

    /**
     * Metoda principală de analiză a textului
     * Citește conținutul fișierului, calculează statisticile textului și afișează rezultatele.
     */
    public void analyze() {
        try {
            // Citirea conținutului fișierului
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            CounterFactory factory = new CounterFactory(); // Crearea instanței fabricii de contorizare
            TextStatistics statistics = new TextStatistics(content); // Calcularea statisticilor textului

            // Afișarea statisticilor textului
            System.out.println(statistics);

            // Contorizarea și afișarea cuvintelor sau frazelor cele mai frecvente
            if (phraseSize == 1) {
                WordCounter wordCounter = factory.createWordCounter(); // Crearea instanței pentru contorizarea cuvintelor
                Map<String, Integer> wordCounts = wordCounter.countWords(content, top); // Contorizarea cuvintelor
                printCounts(wordCounts); // Afișarea contorizării cuvintelor
            } else {
                PhraseCounter phraseCounter = factory.createPhraseCounter(phraseSize); // Crearea instanței pentru contorizarea frazelor
                Map<String, Integer> phraseCounts = phraseCounter.countPhrases(content, top); // Contorizarea frazelor
                printCounts(phraseCounts); // Afișarea contorizării frazelor
            }
        } catch (IOException e) {
            // Gestionarea erorilor de citire a fișierului
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Metodă pentru a afișa contorizările într-un format tabelar
     *
     * @param counts Harta care conține frazele/cuvintele și numărul lor de apariții
     */
    private void printCounts(Map<String, Integer> counts) {
        System.out.println("+--------------------+-------+");
        System.out.println("| Phrases            | Count |");
        System.out.println("+--------------------+-------+");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.printf("| %-18s | %5d |\n", entry.getKey(), entry.getValue()); // Afișarea fiecărei fraze/cuvânt și numărul său de apariții
        }
        System.out.println("+--------------------+-------+");
    }
}
