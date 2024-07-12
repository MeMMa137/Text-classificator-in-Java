
package identificatesto;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class lunghezzaPeriodo {
    public static void _main(String[] args) {
        //lettura file
        
        //CAMBIARE CON FILE DA LEGGERE
        String nomeFile = "C:\\Users\\studente\\Desktop\\IdentificaTesto-20231121T081940Z-001\\IdentificaTesto\\testi\\Romanzi\\Il_Gattopardo.txt"; // Sostituisci con il percorso del tuo file
        List<Integer> listaDiIntero = new ArrayList<>();
        try (FileReader fileReader = new FileReader(nomeFile)) {
            int carattere;
            int conteggioCaratteri = 0, nCaratteriTotali=0, nElementiLista=0;
            

            while ((carattere = fileReader.read()) != -1) { //finchè non finisce file

                if (carattere != '.') { //se non trova un punto
                    conteggioCaratteri++; //aumenta il numero di caratteri nel periodo
                }
}
