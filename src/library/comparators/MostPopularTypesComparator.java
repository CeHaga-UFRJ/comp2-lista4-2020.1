package library.comparators;

import library.entities.Type;

import java.util.Comparator;

public class MostPopularTypesComparator implements Comparator<Type> {
    @Override
    public int compare(Type t1, Type t2) {
        return t2.getTimesBorrowed() - t1.getTimesBorrowed();
    }
}
