package identificatesto;

import java.text.Normalizer;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class PercRime {

    //  DA LEGGERE:
    //      per effettuare un test eseguire il TestAndBenchMark nella cartella Test Packages
    //      assicurandosi che il nome del file preso sia coerente con il file.txt di prova inserito
    //
    //      Lavoro da finire:
    //          -AGGIORNARE I COMMENTI UNA VOLTA CHE SI AGGIORNA IL PROGETTO.
    public final float controllaRime(LinkedList<String> righe) {
        //eliminazione di eventuali acapi a vuoto prima dei confronti
        for (int i = 0; i < righe.size(); i++) {
            if (righe.get(i).trim().isEmpty()) {
                righe.remove(i);
            }
        }
        //controllo parallelo tramite thread

        //Creazione dei threads
        ControlloAABB cAABB = new ControlloAABB(righe);
        ControlloABAB cABAB = new ControlloABAB(righe);
        ControlloABBA cABBA = new ControlloABBA(righe);
        ControlloCDECDE cCDECDE = new ControlloCDECDE(righe);
        ControlloCDEEDC cCDEEDC = new ControlloCDEEDC(righe);
        ControlloABABCB cABABCB = new ControlloABABCB(righe);

        //Inizio parallelizzazione
        cAABB.start();
        cABAB.start();
        cABBA.start();
        cCDECDE.start();
        cCDEEDC.start();
        cABABCB.start();

        //riunione dei thread una volta che finiscono
        try {
            cAABB.join();
            cABAB.join();
            cABBA.join();
            cCDECDE.join();
            cCDEEDC.join();
            cABABCB.join();
        } catch (Exception e) {
        }
        //sommatoria
        float somma = 0;
        somma += cAABB.rimeTrovate;
        somma += cABAB.rimeTrovate;
        somma += cABBA.rimeTrovate;
        somma += cCDECDE.rimeTrovate;
        somma += cCDEEDC.rimeTrovate;
        somma += cABABCB.rimeTrovate;

        //return di % rime ripetto righe
        return somma / righe.size();
    }

    // Metodo per confrontare le rime tra due stringhe
    private static boolean areWordsRhyming(String word1, String word2) {
        // Lunghezza delle stringhe
        int length1 = word1.length();
        int length2 = word2.length();

        // Trova la lunghezza minima tra le due stringhe
        int minLength = Math.min(length1, length2);
        // Conto il numero di volte che un carattere fa rima con un altro
        int j = 0;

        // Confronta e scorre i caratteri alla fine delle due stringhe contando quante volte rimano
        for (int i = 1; i <= minLength; i++) {
            if (word1.endsWith(word2.substring(length2 - i))) {
                j++;
            }
        }

        //se la lungezza min e' 1 e il numero di caratteri che rimano e' 1 (true)
        if (minLength == 1 && j >= 1) {
            return true;
        } //se la rima e' piu lunga di 1 carattere
        else {
            return j > 1;
        }
    }

    // Metodo per confrontare le rime tra due stringhe
    private static boolean areWordsRhyming(String word1, String word2, String word3) {
        // Lunghezza delle stringhe
        int length1 = word1.length();
        int length2 = word2.length();
        int length3 = word3.length();

        // Trova la lunghezza minima tra le due stringhe
        int minLength = Math.min(Math.min(length1, length2), length3);
        // Conto il numero di volte che un carattere fa rima con un altro
        int j = 0;

        // Confronta e scorre i caratteri alla fine delle due stringhe contando quante volte rimano
        for (int i = 1; i <= minLength; i++) {
            if (word1.endsWith(word2.substring(length2 - i))
                    && word1.endsWith(word3.substring(length3 - i))) {
                j++;
            }
        }

        //se la lungezza min e' 1 e il numero di caratteri che rimano e' 1 (true)
        if (minLength == 1 && j >= 1) {
            return true;
        } //se la rima e' piu lunga di 1 carattere
        else {
            return j > 1;
        }
    }

    // Metodo per rimuovere la punteggiatura da una stringa
    public static String removePunctuation(String input) {
        // Utilizza un'espressione regolare per sostituire la punteggiatura con una stringa vuota
        // [ \\p{Punct} && [^'] ] | \\s+
        // [insieme di punteggiatura (&&) apparte l'apostrofo] oppure /uno o piu spazi
        return input.replaceAll("[\\p{Punct}&&[^']]|\\s+", "");
    }

    // Metodo per rimuovere gli accenti
    public static String removeAccents(String input) {
        //normalizza una stringa (àèìòù) --> (a?e?i?o?u?)
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        // Crea un pattern che corrisponde a tutti i diacritici (caratteri accentati)
        // ...rappresentati come '?' nella stringa normlizzata (a?e?i?o?u?)
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        // Utilizza il pattern per trovare tutti i diacritici nella stringa normalizzata ('?')
        // e li sostituisce con una stringa vuota (rimuovendoli)
        // cosi da  rendere la stringa normalizzata (a?e?i?o?u?) --> (aeiou)
        return pattern.matcher(normalized).replaceAll("");
    }

    private final static class ControlloAABB extends Thread {

        LinkedList<String> righe;
        int rimeTrovate;

    }

    public ControlloAABB(LinkedList<String> righe) {
        this.righe = righe;
    }

    @Override
    public void run() {
        rimeTrovate = controllaAABB(righe);
    }

    private static int controllaAABB(LinkedList<String> righe) {

        String firstAStr = "";
        String firstBStr = "";
        String secondAStr = "";
        String secondBStr = "";

        //"nRigheAllaVolta" e' il numero di righe che andiamo a controllare alla volta (in questo caso 4 --> AABB)
        //nota: senza la limitazione di "nRigheAllaVolta" si va a "sforare" la lista
        int nRigheAllaVolta = 4;
        //Variabile di incremento per contare il numero di rime
        int j = 0;

        for (int i = 0; i < righe.size() - nRigheAllaVolta; i++) {
            try {
                //prendo l'ultima parola della righa usando .split
                //che retituisce un array di parole separate da " " (spazi)
                //andando a prendere l'ultima parola (lenght -1)
                //Nota: 
                //  se cambi la "i" o " " nella parte di sinistra devi farlo anche a destra
                firstAStr = righe.get(i).split(" ")[righe.get(i).split(" ").length - 1];
                secondAStr = righe.get(i + 1).split(" ")[righe.get(i + 1).split(" ").length - 1];
                firstBStr = righe.get(i + 2).split(" ")[righe.get(i + 2).split(" ").length - 1];
                secondBStr = righe.get(i + 3).split(" ")[righe.get(i + 3).split(" ").length - 1];
                //eliminazione di eventuali caratteri indesiderati di fine stringa come ; , " - _ < > . ! | ) (
                firstAStr = removePunctuation(firstAStr);
                secondAStr = removePunctuation(secondAStr);
                firstBStr = removePunctuation(firstBStr);
                secondBStr = removePunctuation(secondBStr);
                //eliminazione degli accenti
                firstAStr = removeAccents(firstAStr);
                secondAStr = removeAccents(secondAStr);
                firstBStr = removeAccents(firstBStr);
                secondBStr = removeAccents(secondBStr);
                //Incremento se AA e BB rimano contemporaneamente
                if (areWordsRhyming(firstAStr, secondAStr) && areWordsRhyming(firstBStr, secondBStr)) {
                    j++;
                }
            } catch (Exception e) {
                return j;
            }
        }
        //restituisco il numero di rime
        return j;
    }

    private final static class ControlloABAB extends Thread {

        LinkedList<String> righe;
        int rimeTrovate;

        public ControlloABAB(LinkedList<String> righe) {
            this.righe = righe;
        }

        @Override
        public void run() {
            rimeTrovate = controllaABAB(righe);
        }

        private static int controllaABAB(LinkedList<String> righe) {

            String firstAStr = "";
            String firstBStr = "";
            String secondAStr = "";
            String secondBStr = "";

            //"nRigheAllaVolta" e' il numero di righe che andiamo a controllare alla volta (in questo caso 4 --> AABB)
            //nota: senza la limitazione di "nRigheAllaVolta" si va a "sforare" la lista
            int nRigheAllaVolta = 4;
            //Variabile di incremento per contare il numero di rime
            int j = 0;

            for (int i = 0; i < righe.size() - nRigheAllaVolta; i++) {
                try {
                    //prendo l'ultima parola della righa usando .split
                    //che retituisce un array di parole separate da " " (spazi)
                    //andando a prendere l'ultima parola (lenght -1)
                    //Nota: 
                    //  se cambi la "i" o " " nella parte di sinistra devi farlo anche a destra
                    firstAStr = righe.get(i).split(" ")[righe.get(i).split(" ").length - 1];
                    secondAStr = righe.get(i + 2).split(" ")[righe.get(i + 2).split(" ").length - 1];
                    firstBStr = righe.get(i + 1).split(" ")[righe.get(i + 1).split(" ").length - 1];
                    secondBStr = righe.get(i + 3).split(" ")[righe.get(i + 3).split(" ").length - 1];
                    //eliminazione di eventuali caratteri indesiderati di fine stringa come ; , " - _ < > . ! | ) (
                    firstAStr = removePunctuation(firstAStr);
                    secondAStr = removePunctuation(secondAStr);
                    firstBStr = removePunctuation(firstBStr);
                    secondBStr = removePunctuation(secondBStr);
                    //eliminazione degli accenti
                    firstAStr = removeAccents(firstAStr);
                    secondAStr = removeAccents(secondAStr);
                    firstBStr = removeAccents(firstBStr);
                    secondBStr = removeAccents(secondBStr);
                    //Incremento se ABAB rimano
                    if (areWordsRhyming(firstAStr, secondAStr) && areWordsRhyming(firstBStr, secondBStr)) {
                        j++;
                    }
                } catch (Exception e) {
                    return j;
                }
            }
            //restituisco il numero di rime
            return j;

        }
    }
    
     private final static class ControlloABBA extends Thread{
        LinkedList<String> righe;
        int rimeTrovate;
        public ControlloABBA(LinkedList<String> righe) {
            this.righe = righe;
        }
        @Override
        public void run(){
            rimeTrovate = controllaABBA(righe);
        }
        
    private final static class ControlloCDECDE extends Thread{
        LinkedList<String> righe;
        int rimeTrovate;
        public ControlloCDECDE(LinkedList<String> righe) {
            this.righe = righe;
        }
        @Override
        public void run(){
            rimeTrovate = controllaCDECDE(righe);
        }   
        
        private final static class ControlloCDEEDC extends Thread{
        LinkedList<String> righe;
        int rimeTrovate;
        public ControlloCDEEDC(LinkedList<String> righe) {
            this.righe = righe;
        }
        @Override
        public void run(){
            rimeTrovate = controllaCDEEDC(righe);
        }
        
         private final static class ControlloABABCB extends Thread{
        LinkedList<String> righe;
        int rimeTrovate;
        public ControlloABABCB(LinkedList<String> righe) {
            this.righe = righe;
        }
        @Override
        public void run(){
            rimeTrovate = controlloABABCB(righe);
        }

}
