
package identificatesto;

import java.io.FileReader;
import java.io.IOException;
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
                }else { //se trova un punto
                    nCaratteriTotali=nCaratteriTotali+conteggioCaratteri; //aggiunge gli elementi al conteggio numeri totali
                    listaDiIntero.add(conteggioCaratteri); //aggiunge il "salvataggio" del numero di caratteri in un periodo, e lo inserisce in una lista
                    conteggioCaratteri = 0; //azzera il conteggio per iniziare di nuovo
                    nElementiLista++; //aggiunge al conteggio di elementi presenti nella lista
                    
                }

            }
            System.out.println("i caratteri nei periodi sono: " + listaDiIntero+"\n");
            System.out.println("la media dei caratteri nei periodi sono: " + nCaratteriTotali / nElementiLista);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
