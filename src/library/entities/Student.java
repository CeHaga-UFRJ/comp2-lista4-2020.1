package library.entities;

import library.interfaces.Notifiable;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable, Notifiable {
    public static final long serialVersionUID = 4000L;

    private static int lastId = 1;

    private int studentId;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private char gender;
    private String classCode;
    private int points;
    private int borrowedBooks;
    private int actualBooks;

    public Student(int studentId, String name, String surname, LocalDate birthdate, char gender, String classCode, int points) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.classCode = classCode;
        this.points = points;
        actualBooks = 0;
        if(lastId < studentId) lastId = studentId;
    }

    public Student(String name, String surname, LocalDate birthdate, char gender, String classCode, int points) {
        this.studentId = ++lastId;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.classCode = classCode;
        this.points = points;
        actualBooks = 0;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public char getGender() {
        return gender;
    }

    public String getClassCode() {
        return classCode;
    }

    public int getPoints() {
        return points;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void addBorrowedBooks() {
        this.borrowedBooks++;
    }

    public void addPoints(int points){
        this.points += points;
    }

    public void addActualBooks(){
        this.actualBooks++;
    }

    public int getActualBooks(){
        return actualBooks;
    }

    public void removeActualBooks(){
        this.actualBooks--;
    }

    @Override
    public String toString(){
        return String.format("%s %s(id: %d). Pegou %d livros emprestados e possui %d pontos", name, surname, studentId, borrowedBooks, points);
    }
}
