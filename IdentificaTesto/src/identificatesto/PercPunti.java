package identificatesto;

import java.util.LinkedList;

class PercPunti {
    private LinkedList<String> lt;
    PercPunti(LinkedList<String> lt) {
        this.lt=lt;
    }

    float calcpercPunti() {
        int ns=0,n=0;
        for(int i=1;i<lt.size();i++) {
            char[] riga = lt.get(i).toCharArray();
            for(int j=0;j<riga.length;j++)
                if(riga[j]=='.')
                    ns++;
            n+=riga.length;
        }
        return (float)ns/n;
    }    
}
