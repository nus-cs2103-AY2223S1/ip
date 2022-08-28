package unc;

public class UncException extends Exception{
    public UncException(String message) {
        super(message);
    }

    public UncException(String message, Throwable error) {
        super(message, error);
    }

}
