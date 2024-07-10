package identificatesto;

import java.io.File;
import java.util.LinkedList;

public class StrutturaDati {
    public Nodo h;
    int length;
    public String tipoTesto, titolo;
    int numerotesto;
    File file;
    StrutturaDati(String tipoTesto, int numerotesto, File file) {
        this.tipoTesto = tipoTesto;
        this.numerotesto = numerotesto;
        this.file=file;
        titolo = file.getName();
        h = null;
        length = 0;
        calcolaStats();
    }
    double getValore(String tipoStat) {
        Nodo p = h;
        while(p!=null) {
            if(p.nome.equals(tipoStat))
                return p.valore;
            p=p.next;
        }
        return 0;
    }
    
void stampa() {
        Nodo p = h;
        while(p != null) {
            System.out.print("      "+p.nome + ": "+Float.toString(p.valore) + "\n");
            p = p.next;
        }
    }
    Nodo getNodeByIndex(int index){
        Nodo p = h;
        int i = 0;
        while(i != index){
            p = p.next;
            i++;
        }
        if(p!=null)
            return p;
        else
            return null;
    }
    
    void calcolaStats() {
        Interfaccia i = new Interfaccia(file);
        i.leggiFile();
        LinkedList<String> lt = i.getLinkedList();
        titolo = lt.get(0).trim();
        PercSpazi ps = new PercSpazi(lt);
        float vps = ps.calcolaPercSpazi();
        insStat("percSpazi",vps);
        PercVirgole pv = new PercVirgole(lt);
        float vpv = pv.calcolaPercVirgole();
        insStat("percVirgole",vpv);
        PercentualeAcapo pac = new PercentualeAcapo(lt);
        float vpac = pac.calPercentualeAcapo();
        insStat("percACapo",vpac);
        PercPunti pp = new PercPunti(lt);
        float vpp = pp.calcpercPunti();
        insStat("percPunti",vpp);
        PercRime percRime = new PercRime();
        float vpr = percRime.controllaRime(lt);
        insStat("PercRime", vpr);
    }
    
    void insStat(String nome, float valore) {
        Nodo n = new Nodo(nome, valore);
        if(h == null)
            h = n;
        else {
            Nodo p = h;
            while(p.next != null){
                p = p.next;
            }
            p.next = n;
        }
        length++;
    }
    
    
}
    
