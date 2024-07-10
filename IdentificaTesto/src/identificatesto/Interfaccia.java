package identificatesto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class Interfaccia{
    private LinkedList<String> listTesti;
    private File file;
    
    public Interfaccia(File file) {
        listTesti = new LinkedList<>();
        this.file=file;
    }
    
final public void stampaTest(){
        //per ogni elemento stampa
        for(String str : listTesti)
            System.out.println(str);
    }
    public LinkedList<String> getLinkedList(){
        return listTesti;
    }
    
}
