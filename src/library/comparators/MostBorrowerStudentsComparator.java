package library.comparators;

import library.entities.Student;

import java.util.Comparator;

/**
 * Comparador entre estudantes que mais pegaram livros. Ordem descrescente
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class MostBorrowerStudentsComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        if(s1.getStudentId() == s2.getStudentId()) return 0;
        int diff = s2.getBorrowedBooks() - s1.getBorrowedBooks();
        if(diff == 0) return -1;
        return diff;
    }
}
