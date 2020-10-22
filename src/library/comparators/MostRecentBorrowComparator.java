package library.comparators;

import library.entities.Borrow;

import java.util.Comparator;

public class MostRecentBorrowComparator implements Comparator<Borrow> {
    @Override
    public int compare(Borrow b1, Borrow b2) {
        if(b1.getTakenDate().isBefore(b2.getTakenDate())) return 1;
        if(b1.getTakenDate().isAfter(b2.getTakenDate())) return -1;
        if(b1.getBroughtDate().isBefore(b2.getBroughtDate())) return 1;
        if(b1.getBroughtDate().isAfter(b2.getBroughtDate())) return -1;
        return 0;
    }
}
