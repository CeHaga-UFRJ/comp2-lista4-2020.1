package library.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    public static final long serialVersionUID = 4000L;

    private int studentId;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private char gender;
    private String classCode;
    private int points;
    private int borrowedBooks;

    public Student(int studentId, String name, String surname, LocalDate birthdate, char gender, String classCode, int points) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.classCode = classCode;
        this.points = points;
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
}
