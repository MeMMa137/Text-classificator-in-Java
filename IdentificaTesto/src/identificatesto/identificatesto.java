
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

    public static void exportCsv(LinkedList<StrutturaDati>[] alsd, Statistiche[] s) throws FileNotFoundException {
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
                   
                    // ( p.getNodeByIndex(i).valore + "\n");
                }
                b.write("\n");
                b.write("\n");
                
                
                for(int j=0;j<s.length;j++) {
            //b.write(s[i].tipoTesto+",");
            b.write(s[i].tipoTesto + "," + "pp-media,"+s[j].pp.media+", devStd,"+s[j].pp.devStd + "\n");
            b.write(s[i].tipoTesto + "," +"ps-media,"+s[j].ps.media+", devStd,"+s[j].ps.devStd + "\n");
            b.write(s[i].tipoTesto + "," +"pac-media,"+s[j].pac.media+", devStd,"+s[j].pac.devStd + "\n");
            b.write(s[i].tipoTesto + "," +"pv-media,"+s[j].pv.media+", devStd,"+s[j].pv.devStd + "\n");
        }
                b.write("\n");
                b.write("\n");

            }
            b.close();
        } catch (Exception e) {
            SOP("no");
        }

    }
    
   
    static void SOP(String s) {
        System.out.println(s);
    }

    static void stampa(LinkedList<StrutturaDati>[] alsd) {
        for (int i = 0; i < alsd.length; i++) {
            SOP("Tipo testo: " + i);
            for (int j = 0; j < alsd[i].size(); j++) {
                SOP("   Testo numero: " + j);
                alsd[i].get(j).stampa();
            }
        }
    }
    
    static double media(LinkedList<Double> x) {
        double m=0;
        for(double xi: x){
            m+=xi;
        }
        return m/x.size();
    }
    
    static Statistiche[] calcolaPerClassificazione(LinkedList<StrutturaDati>[] llsd) {
        Statistiche[] s = new Statistiche[4];
        String[] tipiTesto = {"Fumetti","Poesie","Romanzi","Tecnico"};
        for(int i=0;i<tipiTesto.length;i++) {
            s[i] = new Statistiche();
            s[i].tipoTesto = tipiTesto[i];
            LinkedList<Double> x = new LinkedList<>();
            for(StrutturaDati sd: llsd[i])
                x.add(sd.getValore("percPunti"));
            s[i].pp.media = media(x);
            s[i].pp.devStd = devStd(x);
            x.clear();
            for(StrutturaDati sd: llsd[i])
                x.add(sd.getValore("percVirgole"));
            s[i].pv.media = media(x);
            s[i].pv.devStd = devStd(x);
            x.clear();
            for(StrutturaDati sd: llsd[i])
                x.add(sd.getValore("percACapo"));
            s[i].pac.media = media(x);
            s[i].pac.devStd = devStd(x);
            x.clear();
            for(StrutturaDati sd: llsd[i])
                x.add(sd.getValore("percSpazi"));
            s[i].ps.media = media(x);
            s[i].ps.devStd = devStd(x);
        }
        return s;
    }
    
    static void stampaStats(Statistiche[] s) {
        for(int i=0;i<s.length;i++) {
            SOP(s[i].tipoTesto+":");
            SOP("   pp => media:"+s[i].pp.media+", devStd:"+s[i].pp.devStd);
            SOP("   ps => media:"+s[i].ps.media+", devStd:"+s[i].ps.devStd);
            SOP("   pac => media:"+s[i].pac.media+", devStd:"+s[i].pac.devStd);
            SOP("   pv => media:"+s[i].pv.media+", devStd:"+s[i].pv.devStd);
        }
    }
    
    
    
}


    
