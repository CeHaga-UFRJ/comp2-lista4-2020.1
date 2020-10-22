package library.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Borrow implements Serializable {
    public static final long serialVersionUID = 3000L;

    private int borrowId;
    private int studentId;
    private int bookId;
    private LocalDate takenDate;
    private LocalDate broughtDate;
    private Student student;
    private Book book;

    public Borrow(int borrowId, int studentId, int bookId, LocalDate takenDate, LocalDate broughtDate) {
        this.borrowId = borrowId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.takenDate = takenDate;
        this.broughtDate = broughtDate;
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

    public LocalDate getTakenDate() {
        return takenDate;
    }

    public LocalDate getBroughtDate() {
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
}
