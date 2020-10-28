package library.comparators;

import library.entities.Borrow;

import java.util.Comparator;

/**
 * Comparador entre empréstimos mais recentes. Ordem descrescente
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class MostRecentBorrowComparator implements Comparator<Borrow> {
    @Override
    public int compare(Borrow b1, Borrow b2) {
        if(b1.getBorrowId() == b2.getBorrowId()) return 0;
        if(b1.getTakenDate().isBefore(b2.getTakenDate())) return 1;
        if(b1.getTakenDate().isAfter(b2.getTakenDate())) return -1;
        if(b1.getBroughtDate() == null) return -1;
        if(b2.getBroughtDate() == null) return 1;
        if(b1.getBroughtDate().isBefore(b2.getBroughtDate())) return 1;
        return -1;
    }
}
