package org.example;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Clasa de testare pentru TextAnalyzer, care verifică funcționalitatea componentelor individuale
 * și asigură acuratețea rezultatelor.
 */
public class TextAnalyzerTest {

    /**
     * Testează funcționalitatea WordCounter pentru un conținut simplu.
     */
    @Test
    public void testWordCounter() {
        String content = "hello world hello";
        WordCounter wordCounter = new WordCounter();
        Map<String, Integer> result = wordCounter.countWords(content, 2);
        assertEquals(2, result.size());
        assertEquals(Integer.valueOf(2), result.get("hello"));
        assertEquals(Integer.valueOf(1), result.get("world"));
    }

    /**
     * Testează funcționalitatea PhraseCounter pentru un conținut simplu.
     */
    @Test
    public void testPhraseCounter() {
        String content = "hello world hello world";
        PhraseCounter phraseCounter = new PhraseCounter(2);
        Map<String, Integer> result = phraseCounter.countPhrases(content, 2);
        assertEquals(2, result.size());
        assertEquals(Integer.valueOf(2), result.get("hello world"));
    }

    /**
     * Testează calcularea statisticilor textului (numărul de cuvinte și propoziții).
     */
    @Test
    public void testTextStatistics() {
        String content = "Hello world. Hello!";
        TextStatistics statistics = new TextStatistics(content);
        assertEquals(3, statistics.wordCount);
        assertEquals(2, statistics.sentenceCount);
    }

    /**
     * Testează funcționalitatea completă a TextAnalyzer pentru contorizarea cuvintelor.
     */
    @Test
    public void testTextAnalyzerWithWords() {
        String filePath = "testfile.txt";
        try {
            Files.write(Paths.get(filePath), "hello world hello".getBytes());
        } catch (IOException e) {
            fail("Error writing test file: " + e.getMessage());
        }

        TextAnalyzer analyzer = new TextAnalyzer(filePath, 2, 1);
        analyzer.analyze();
    }

    /**
     * Testează funcționalitatea completă a TextAnalyzer pentru contorizarea frazelor.
     */
    @Test
    public void testTextAnalyzerWithPhrases() {
        String filePath = "testfile.txt";
        try {
            Files.write(Paths.get(filePath), "hello world hello world".getBytes());
        } catch (IOException e) {
            fail("Error writing test file: " + e.getMessage());
        }

        TextAnalyzer analyzer = new TextAnalyzer(filePath, 2, 2);
        analyzer.analyze();
    }

    /**
     * Testează contorizarea frazelor pentru un conținut gol.
     */
    @Test
    public void testPhraseCounterWithEmptyContent() {
        String content = "";
        PhraseCounter phraseCounter = new PhraseCounter(2);
        Map<String, Integer> result = phraseCounter.countPhrases(content, 5);
        assertTrue(result.isEmpty());
    }

    /**
     * Testează calcularea statisticilor textului pentru un conținut gol.
     */
    @Test
    public void testTextStatisticsWithEmptyContent() {
        String content = "";
        TextStatistics statistics = new TextStatistics(content);
        assertEquals(0, statistics.wordCount);
        assertEquals(0, statistics.sentenceCount);
    }

    /**
     * Testează gestionarea erorilor pentru un fișier invalid.
     */
    @Test
    public void testTextAnalyzerWithInvalidFile() {
        TextAnalyzer analyzer = new TextAnalyzer("invalidfile.txt", 2, 1);
        analyzer.analyze();  // Ar trebui să gestioneze excepția și să nu arunce nicio eroare
    }

    /**
     * Testează funcționalitatea completă a TextAnalyzer pentru un fișier mare.
     */
    @Test
    public void testTextAnalyzerWithLargeInput() {
        StringBuilder contentBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            contentBuilder.append("lorem ipsum dolor sit amet ");
        }
        String content = contentBuilder.toString();

        String filePath = "largefile.txt";
        try {
            Files.write(Paths.get(filePath), content.getBytes());
        } catch (IOException e) {
            fail("Error writing test file: " + e.getMessage());
        }

        TextAnalyzer analyzer = new TextAnalyzer(filePath, 5, 3);
        analyzer.analyze();
    }

    /**
     * Testează funcționalitatea TextAnalyzer pentru dimensiuni diferite ale frazelor.
     */
    @Test
    public void testTextAnalyzerWithDifferentPhraseSizes() {
        String filePath = "testfile.txt";
        try {
            Files.write(Paths.get(filePath), "hello world hello universe hello world".getBytes());
        } catch (IOException e) {
            fail("Error writing test file: " + e.getMessage());
        }

        TextAnalyzer analyzer1 = new TextAnalyzer(filePath, 2, 1);
        analyzer1.analyze();

        TextAnalyzer analyzer2 = new TextAnalyzer(filePath, 2, 2);
        analyzer2.analyze();

        TextAnalyzer analyzer3 = new TextAnalyzer(filePath, 2, 3);
        analyzer3.analyze();
    }

    /**
     * Testează calcularea statisticilor textului pentru un conținut cu caractere speciale.
     */
    @Test
    public void testTextStatisticsWithSpecialCharacters() {
        String content = "Hello, world! How are you? I'm fine. Thanks for asking.";
        TextStatistics statistics = new TextStatistics(content);
        assertEquals(10, statistics.wordCount); // "I'm" ar trebui să fie considerat un singur cuvânt
        assertEquals(4, statistics.sentenceCount);
    }

    /**
     * Testează funcționalitatea TextAnalyzer pentru un text cu multă punctuație.
     */
    @Test
    public void testTextAnalyzerWithPunctuation() {
        String filePath = "punctuationfile.txt";
        try {
            Files.write(Paths.get(filePath), "Hello, world! How are you? Hello again.".getBytes());
        } catch (IOException e) {
            fail("Error writing test file: " + e.getMessage());
        }

        TextAnalyzer analyzer = new TextAnalyzer(filePath, 3, 1);
        analyzer.analyze();
    }
}
