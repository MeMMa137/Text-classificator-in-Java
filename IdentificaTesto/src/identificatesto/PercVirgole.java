package identificatesto;

import java.util.LinkedList;

class PercVirgole {
    private LinkedList<String> lt;
    PercVirgole(LinkedList<String> lt) {
        this.lt=lt;
    }
    float calcolaPercVirgole() {
        int ns=0,n=0;
        for(int i=1;i<lt.size();i++) {
            char[] riga = lt.get(i).toCharArray();
            for(int j=0;j<riga.length;j++)
                if(riga[j]==',')
                    ns++;
            n+=riga.length;
        }
        return (float)ns/n;
    }
}
