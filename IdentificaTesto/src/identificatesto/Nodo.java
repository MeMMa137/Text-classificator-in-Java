package identificatesto;


public class Nodo {
    String nome;
    float valore;
    Nodo next;
    Nodo(String nome, float valore){
        this.nome = nome;
        this.valore = valore;
        next=null;
    }
}
