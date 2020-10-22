package library.comparators;

import library.entities.Student;

import java.util.Comparator;

public class MostBorrowerStudentsComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s2.getBorrowedBooks() - s1.getBorrowedBooks();
    }
}
