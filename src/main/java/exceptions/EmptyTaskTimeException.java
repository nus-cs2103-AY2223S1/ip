package exceptions;

public class EmptyTaskTimeException extends Exception {

    public EmptyTaskTimeException() {
        super ("OOPS!!! You need to specify a time.");
    }

}
