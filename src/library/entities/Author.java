package library.entities;

import library.interfaces.Notifiable;

import java.io.Serializable;

/**
 * Classe para representar um autor
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class Author implements Serializable, Notifiable {
    public static final long serialVersionUID = 1000L;

    private static int lastId = 1;

    private int authorId;
    private String name;
    private String surname;
    private int timesBorrowed;

    public Author(int authorId, String name, String surname){
        this.authorId = authorId;
        this.name = name;
        this.surname = surname;
        if(lastId < authorId) lastId = authorId;
    }

    public Author(String name, String surname){
        this.name = name;
        this.surname = surname;
        this.authorId = ++lastId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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
        return String.format("%s %s(id : %d). Foi pego emprestado %d vezes", name, surname, authorId, timesBorrowed);
    }
}
