package identificatesto;

import java.util.LinkedList;

class PercentualeAcapo {
    private LinkedList<String> lt;
    PercentualeAcapo(LinkedList<String> lt) {
        this.lt=lt;
    }
    float calPercentualeAcapo() {
        int n=0;
        for(int i=1;i<lt.size();i++)
            n+=lt.get(i).length();
        return (float)(lt.size()-1)/n;
    }
}
