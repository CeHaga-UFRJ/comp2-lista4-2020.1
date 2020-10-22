package library.exceptions;

public class NumberOutOfRangeException extends RuntimeException {
    public NumberOutOfRangeException(String msg){
        super(msg);
    }
}
