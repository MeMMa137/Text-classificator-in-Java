
package identificatesto;

import java.util.LinkedList;

class PercSpazi {
    private LinkedList<String> lt;
    PercSpazi(LinkedList<String> lt) {
        this.lt=lt;
    }
    float calcolaPercSpazi() {
        int ns=0,n=0;
        for(int i=1;i<lt.size();i++) {
            char[] riga = lt.get(i).toCharArray();
            for(int j=0;j<riga.length;j++)
                if(riga[j]==' ')
                    ns++;
            n+=riga.length;
        }
        return (float)ns/n;
    }
}
