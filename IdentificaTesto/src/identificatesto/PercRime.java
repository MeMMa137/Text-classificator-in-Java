package identificatesto;

import java.text.Normalizer;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class PercRime {
    
    //  DA LEGGERE:
    //      per effettuare un test eseguire il TestAndBenchMark nella cartella Test Packages
    //      assicurandosi che il nome del file preso sia coerente con il file.txt di prova inserito
    //
    //      Lavoro da finire:
    //          -AGGIORNARE I COMMENTI UNA VOLTA CHE SI AGGIORNA IL PROGETTO.
   
   public final float controllaRime(LinkedList<String> righe) {
      //eliminazione di eventuali acapi a vuoto prima dei confronti
      for(int i=0;i<righe.size();i++){
         if(righe.get(i).trim().isEmpty())
            righe.remove(i);
      }
      //controllo parallelo tramite thread
      
      //Creazione dei threads
      ControlloAABB cAABB = new ControlloAABB(righe);
      ControlloABAB cABAB = new ControlloABAB(righe);
      ControlloABBA cABBA = new ControlloABBA(righe);
      ControlloCDECDE cCDECDE = new ControlloCDECDE(righe);
      ControlloCDEEDC cCDEEDC = new ControlloCDEEDC(righe);
      ControlloABABCB cABABCB = new ControlloABABCB(righe);
      
      //Inizio parallelizzazione
      cAABB.start();
      cABAB.start();
      cABBA.start();
      cCDECDE.start();
      cCDEEDC.start();
      cABABCB.start();
      
