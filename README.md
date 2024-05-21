
# Task Internship

Text Analyzer este un utilitar de linie de comandă pentru analiza textelor, care ajută la găsirea celor mai frecvente fraze și cuvinte dintr-un fișier text furnizat. De asemenea, numără numărul de cuvinte și propoziții.

## Caracteristici
* Numără cuvintele și propozițiile dintr-un text
* Găsește cele mai frecvente cuvinte și fraze dintr-un text
* Suport pentru fraze de diferite dimensiuni (număr de cuvinte)
* Suport pentru fișiere mari

## Utilizare
### Compilare și Ambalare
Pentru a compila și ambala proiectul într-un fișier JAR executabil, folosiți Maven:
mvn clean package


```bash
  mvn clean package
```

## Rulare
Pentru a rula aplicația, folosiți comanda java -jar:

```bash
  java -jar target/textanalyzer-1.0-SNAPSHOT.jar -file=<cale_către_fișier> -top=<număr_top> -phraseSize=<dimensiune_frază>
```

## Structura Proiectului
###  src/main/java/com/example/textanalyzer

* Main.java: Punctul de intrare al aplicației
* TextAnalyzer.java: Clasa principală pentru analiza textului
* PhraseCounter.java: Clasa pentru contorizarea frazelor
* WordCounter.java: Clasa pentru contorizarea cuvintelor
* CounterFactory.java: Fabrica pentru crearea instanțelor de  WordCounter și PhraseCounter
* TextStatistics.java: Clasa pentru calcularea statisticilor textului

#### src/test/java/com/example/textanalyzer

* TextAnalyzerTest.java: Clase de testare pentru funcționalitatea aplicației
