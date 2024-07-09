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
    
