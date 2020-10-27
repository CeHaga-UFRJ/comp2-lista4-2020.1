package library.comparators;

import library.entities.Book;

import java.util.Comparator;

public class MostBorrowedBooksComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        if(b1.getBookId() == b2.getBookId()) return 0;
        int diff = b2.getTimesBorrowed() - b1.getTimesBorrowed();
        if(diff == 0) return -1;
        return diff;
    }
}
