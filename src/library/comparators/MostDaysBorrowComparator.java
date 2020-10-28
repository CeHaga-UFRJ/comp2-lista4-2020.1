package library.comparators;

import library.entities.Borrow;

import java.time.*;
import java.util.Comparator;

/**
 * Comparador entre empréstimos que duraram mais dias. Ordem descrescente
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class MostDaysBorrowComparator implements Comparator<Borrow> {
    @Override
    public int compare(Borrow b1, Borrow b2) {
        if(b1.getBorrowId() == b2.getBorrowId()) return 0;
        LocalDateTime last1 = b1.getBroughtDate();
        if(last1 == null) last1 = LocalDateTime.now();
        LocalDateTime last2 = b2.getBroughtDate();
        if(last2 == null) last2 = LocalDateTime.now();
        Duration d1 = Duration.between(b1.getTakenDate(), last1);
        Duration d2 = Duration.between(b2.getTakenDate(), last2);
        int comp = d2.compareTo(d1);
        if(comp == 0) return -1;
        return comp;
    }
}
