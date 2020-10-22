package library.entities;

import java.io.Serializable;

public class Book implements Serializable {
    public static final long serialVersionUID = 2000L;

    private int bookId;
    private String name;
    private int pageCount;
    private int point;
    private int authorId;
    private int typeId;
    private Author author;
    private Type type;

    public Book(int bookId, String name, int pageCount, int point, int authorId, int typeId) {
        this.bookId = bookId;
        this.name = name;
        this.pageCount = pageCount;
        this.point = point;
        this.authorId = authorId;
        this.typeId = typeId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPoint() {
        return point;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        this.authorId = author.getAuthorId();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        this.typeId = type.getTypeId();
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getTypeId() {
        return typeId;
    }
}
