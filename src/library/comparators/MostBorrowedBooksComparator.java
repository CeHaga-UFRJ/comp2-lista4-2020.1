package library.comparators;

import library.entities.Book;

import java.util.Comparator;

/**
 * Comparador entre livros que mais foram pegos. Ordem descrescente
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class MostBorrowedBooksComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        if(b1.getBookId() == b2.getBookId()) return 0;
        int diff = b2.getTimesBorrowed() - b1.getTimesBorrowed();
        if(diff == 0) return -1;
        return diff;
    }
}
