package exception;

public class DorisException extends Exception {
    public DorisException(String message) {
        super(message);
    }

    @Override
    public String toString(){
        return getMessage();
    }
}
