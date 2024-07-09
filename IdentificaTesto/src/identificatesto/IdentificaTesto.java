
package identificatesto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class identificatesto {
    
    static class Stat {
        double media, devStd;
    }
    static class Statistiche {
        String tipoTesto;
        Stat ps, pp, pv, pac;
        Statistiche() {
            ps= new Stat();
            pp= new Stat();
            pv= new Stat();
            pac= new Stat();
        }
    }

    public static LinkedList<StrutturaDati>[] creaStrutturaDati() {
        String[] tipiTesto = {"Fumetti", "Poesie", "Romanzi", "Tecnico"};
        LinkedList<StrutturaDati>[] alsd = new LinkedList[tipiTesto.length];
        for (int i = 0; i < tipiTesto.length; i++) {
            alsd[i] = new LinkedList<>();
            String tipoTesto = tipiTesto[i];
        }return alsd;
    }
}
    
