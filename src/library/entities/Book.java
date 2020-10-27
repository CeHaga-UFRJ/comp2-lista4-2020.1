package library.entities;

import library.interfaces.Notifiable;

import java.io.Serializable;
import java.util.*;

public class Book implements Serializable, Notifiable {
    public static final long serialVersionUID = 2000L;

    private static int lastId = 1;

    private int bookId;
    private String name;
    private int pageCount;
    private int point;
    private int authorId;
    private int typeId;
    private Author author;
    private Type type;
    private List<Student> borrowedCopies;
    private int timesBorrowed;

    public Book(int bookId, String name, int pageCount, int point, int authorId, int typeId) {
        this.bookId = bookId;
        this.name = name;
        this.pageCount = pageCount;
        this.point = point;
        this.authorId = authorId;
        this.typeId = typeId;
        borrowedCopies = new ArrayList<>();
        if(lastId < bookId) lastId = bookId;
    }

    public Book(String name, int pageCount, int point, Author author, Type type) {
        this.bookId = ++lastId;
        this.name = name;
        this.pageCount = pageCount;
        this.point = point;
        setAuthor(author);
        setType(type);
        borrowedCopies = new ArrayList<>();
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

    public int getTimesBorrowed() {
        return timesBorrowed;
    }

    public void addTimesBorrowed() {
        this.timesBorrowed++;
    }

    public void addBorrowedCopy(Student student){
        this.borrowedCopies.add(student);
    }

    public void removeBorrowedCopy(Student student){
        this.borrowedCopies.remove(student);
    }

    public boolean isBorrowedBy(Student student){
        return borrowedCopies.contains(student);
    }

    public int getActualCopies(){
        return 2 - borrowedCopies.size();
    }

    @Override
    public String toString(){
        return String.format("%s(id: %d), escrito por %s %s(id: %d). Foi pego emprestado %d vezes",
                name, bookId, author.getName(), author.getSurname(), authorId, timesBorrowed);
    }
}
