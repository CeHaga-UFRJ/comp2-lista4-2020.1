package library.exceptions;

/**
 * Exceção para número fora do raio de leitura
 * @author Carlos Bravo - cehaga@dcc.ufrj.br
 */
public class NumberOutOfRangeException extends RuntimeException {
    public NumberOutOfRangeException(String msg){
        super(msg);
    }
}
