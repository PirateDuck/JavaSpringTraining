package spring.tutorial.exceptions;

public class NoStockFoundException extends Exception {
    public NoStockFoundException(String message) {
        super(message);
    }

}