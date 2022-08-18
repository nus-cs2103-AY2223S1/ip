package exceptions;

public class DukeException extends Exception{
    public String errorMessage;

    public DukeException(String e) {
        this.errorMessage = e;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
