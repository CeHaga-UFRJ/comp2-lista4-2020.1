package library.entities;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Borrow implements Serializable {
    public static final long serialVersionUID = 3000L;

    private static int lastId = 1;

    private int borrowId;
    private int studentId;
    private int bookId;
    private LocalDateTime takenDate;
    private LocalDateTime broughtDate;
    private Student student;
    private Book book;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss");

    public Borrow(int borrowId, int studentId, int bookId, LocalDateTime takenDate, LocalDateTime broughtDate) {
        this.borrowId = borrowId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.takenDate = takenDate;
        this.broughtDate = broughtDate;
        if(lastId < borrowId) lastId = borrowId;
    }

    public Borrow(int studentId, int bookId, LocalDateTime takenDate, LocalDateTime broughtDate) {
        this.borrowId = ++lastId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.takenDate = takenDate;
        this.broughtDate = broughtDate;
    }

    public Borrow(int studentId, int bookId, LocalDateTime takenDate) {
        this.borrowId = ++lastId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.takenDate = takenDate;
        this.broughtDate = null;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public LocalDateTime getTakenDate() {
        return takenDate;
    }

    public LocalDateTime getBroughtDate() {
        return broughtDate;
    }

    public Student getStudent() {
        return student;
    }

    public Book getBook() {
        return book;
    }

    public void setStudent(Student student) {
        this.student = student;
        this.studentId = student.getStudentId();
    }

    public void setBook(Book book) {
        this.book = book;
        this.bookId = book.getBookId();
    }

    @Override
    public String toString(){
        LocalDateTime last = broughtDate;
        if(last == null) last = LocalDateTime.now();
        return String.format("Empréstimo realizado por %s(id: %d) no dia %s, o livro pego foi %s(id: %d) e ficou %d dias",
                student.getName(), studentId, takenDate.format(formatter), book.getName(), bookId, Duration.between(takenDate, last).toDays());
    }
}
