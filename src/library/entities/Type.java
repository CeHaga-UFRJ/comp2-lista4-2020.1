package library.entities;

import library.interfaces.Notifiable;

import java.io.Serializable;

/**
 * Classe para representar um estilo
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class Type implements Serializable, Notifiable {
    public static final long serialVersionUID = 5000L;

    private static int lastId = 1;

    private int typeId;
    private String name;
    private int timesBorrowed;

    public Type(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
        if(lastId < typeId) lastId = typeId;
    }

    public Type(String name) {
        this.typeId = ++lastId;
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public int getTimesBorrowed() {
        return timesBorrowed;
    }

    /**
     * Soma 1 à quantidade de vezes pego
     */
    public void addTimesBorrowed() {
        this.timesBorrowed++;
    }

    @Override
    public String toString(){
        return String.format("%s (id: %d). Esse estilo foi pego emprestado %d vezes", name, typeId, timesBorrowed);
    }
}
