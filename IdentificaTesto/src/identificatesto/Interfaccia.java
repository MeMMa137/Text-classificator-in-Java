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
    
    final public void leggiFile() {
        try {
            //cosa che può andare male
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            while (true) {
                String riga = br.readLine();
                if (riga == null) {
                    break;
                }
                if(riga.length()==0 && (listTesti.size()==0 ||  listTesti.size()==2))
                    riga=null;
                else
                    listTesti.add(riga);
               
            }
            br.close();
        } catch (Exception e) {
            //Err
            System.out.println(e.toString()+"classe: file2string Funzione: leggiFile()");
        }
    }
    
}
