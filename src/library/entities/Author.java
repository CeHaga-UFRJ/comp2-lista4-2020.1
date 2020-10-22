package library.entities;

import java.io.Serializable;

public class Author implements Serializable {
    public static final long serialVersionUID = 1000L;

    private int authorId;
    private String name;
    private String surname;

    public Author(int authorId, String name, String surname){
        this.authorId = authorId;
        this.name = name;
        this.surname = surname;
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
}
