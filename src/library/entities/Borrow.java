package library.entities;

import library.interfaces.Notifiable;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe para representar um empréstimo
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class Borrow implements Serializable, Notifiable {
    public static final long serialVersionUID = 3000L;

    private static int lastId = 1;

    private int borrowId;
    private int studentId;
    private int bookId;
    private LocalDateTime takenDate;
    private LocalDateTime broughtDate;
    private Student student;
    private Book book;

    public Borrow(int borrowId, int studentId, int bookId, LocalDateTime takenDate, LocalDateTime broughtDate) {
        this.borrowId = borrowId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.takenDate = takenDate;
        this.broughtDate = broughtDate;
        if(lastId < borrowId) lastId = borrowId;
    }

    public Borrow(Student student, Book book, LocalDateTime takenDate, LocalDateTime broughtDate) {
        this.borrowId = ++lastId;
        setStudent(student);
        setBook(book);
        this.takenDate = takenDate;
        this.broughtDate = broughtDate;
        student.addBorrowedBooks();
        book.addTimesBorrowed();
        book.getType().addTimesBorrowed();
        book.getAuthor().addTimesBorrowed();
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

    public void setBroughtDate(LocalDateTime broughtDate) {
        this.broughtDate = broughtDate;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
        LocalDateTime last = broughtDate;
        String status = "FECHADO";
        if(last == null){
            last = LocalDateTime.now();
            status = "ABERTO";
        }
        return String.format("Empréstimo(id:%d) realizado por %s %s(id: %d) no dia %s, o livro pego foi %s(id: %d) e ficou %d dias. Atualmente o empréstimo está: %s",
                borrowId, student.getName(), student.getSurname(), studentId, takenDate.format(formatter), book.getName(), bookId, Duration.between(takenDate, last).toDays(), status);
    }
}
