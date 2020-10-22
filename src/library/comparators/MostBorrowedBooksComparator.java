package library.comparators;

import library.entities.Book;

import java.util.Comparator;

public class MostBorrowedBooksComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b2.getTimesBorrowed() - b1.getTimesBorrowed();
    }
}
