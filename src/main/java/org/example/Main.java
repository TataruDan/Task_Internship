package org.example;

import java.io.File;

/**
 * Clasa principală a aplicației care rulează utilitarul de analiză a textului.
 */
public class Main {
    public static void main(String[] args) {
        // Verificăm dacă numărul de argumente este mai mic de 3
        if (args.length < 3) {
            System.out.println("Argumente invalide. Utilizare: java -jar myapplication.jar -file=<file> -top=<top> -phraseSize=<phraseSize>");
            return;
        }

        String filePath = null;
        int top = 0;
        int phraseSize = 0;

        // Parcurgem argumentele pentru a obține valorile pentru file, top și phraseSize
        for (String arg : args) {
            if (arg.startsWith("-file=")) {
                filePath = arg.substring(6);
            } else if (arg.startsWith("-top=")) {
                top = Integer.parseInt(arg.substring(5));
            } else if (arg.startsWith("-phraseSize=")) {
                phraseSize = Integer.parseInt(arg.substring(12));
            }
        }

        // Verificăm dacă argumentele sunt valide
        if (filePath == null || top <= 0 || phraseSize <= 0) {
            System.out.println("Argumente invalide. Utilizare: java -jar myapplication.jar -file=<file> -top=<top> -phraseSize=<phraseSize>");
            return;
        }

        // Verificăm dacă fișierul există și este un fișier valid
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Eroare la citirea fișierului: " + filePath);
            return;
        }

        // Creăm o instanță a clasei TextAnalyzer și analizăm fișierul
        TextAnalyzer analyzer = new TextAnalyzer(filePath, top, phraseSize);
        analyzer.analyze();
    }
}
