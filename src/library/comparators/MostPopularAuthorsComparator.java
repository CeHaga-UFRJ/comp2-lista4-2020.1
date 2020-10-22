package library.comparators;

import library.entities.Author;

import java.util.Comparator;

public class MostPopularAuthorsComparator implements Comparator<Author> {
    @Override
    public int compare(Author a1, Author a2) {
        return a2.getTimesBorrowed() - a1.getTimesBorrowed();
    }
}
