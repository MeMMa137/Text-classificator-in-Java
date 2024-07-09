
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
        }try {
                File[] lista = (new File("testi/" + tipoTesto)).listFiles();
                for (int j = 0; j < lista.length; j++) {
                    SOP(tipoTesto + " " + lista[j].getName());
                    StrutturaDati sd = new StrutturaDati(tipoTesto, j, lista[j]);
                    alsd[i].add(sd);
                    
                }
            } catch (Exception e) {
            }
            //StrutturaDati sd = new StrutturaDati(tipoTesto);
        
        return alsd;
    }

    public static void exportCsv(LinkedList<StrutturaDati>[] alsd, identificatesto.identificatesto.Statistiche[] s) throws FileNotFoundException {
        try {
            FileWriter f = new FileWriter("output.csv");
            BufferedWriter b = new BufferedWriter(f);
            b.write("Tipo,Titolo,%Spazi,%Virgole,%ACapo,%Punti,%Rime\n");
            for (int i = 0; i < alsd.length; i++) {
                //Nodo n = alsd.getNodeByIndex(i);
                for (int j = 0; j < alsd[i].size(); j++) {
                    StrutturaDati p = alsd[i].get(j);
                    b.write(p.tipoTesto + "," + p.titolo.replace(",", "") + ",");
                    for(int k = 0; k<p.length; k++) {
                        b.write(p.getNodeByIndex(k).valore+",");
                    }
                    b.write("\n");
    }
}
}
}
}


    
