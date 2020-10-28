package library.comparators;

import library.entities.Author;

import java.util.Comparator;

public class MostPopularAuthorsComparator implements Comparator<Author> {
    @Override
    public int compare(Author a1, Author a2) {
        if(a1.getAuthorId() == a2.getAuthorId()) return 0;
        int diff = a2.getTimesBorrowed() - a1.getTimesBorrowed();
        if(diff == 0) return -1;
        return diff;
    }
}
