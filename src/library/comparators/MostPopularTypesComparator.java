package library.comparators;

import library.entities.Type;

import java.util.Comparator;

/**
 * Comparador entre estilos que mais foram pegos. Ordem descrescente
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class MostPopularTypesComparator implements Comparator<Type> {
    @Override
    public int compare(Type t1, Type t2) {
        if(t1.getTypeId() == t2.getTypeId()) return 0;
        int diff = t2.getTimesBorrowed() - t1.getTimesBorrowed();
        if(diff == 0) return -1;
        return diff;
    }
}
