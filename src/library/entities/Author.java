package library.entities;

public class Author {
    private int authorId;
    private String name;
    private String surname;

    public Author(int authorId, String name, String surname) {
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
