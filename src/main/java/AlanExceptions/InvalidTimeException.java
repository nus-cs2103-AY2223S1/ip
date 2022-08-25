package AlanExceptions;

public class InvalidTimeException extends AlanException {
    public InvalidTimeException() {
        super("You could entire the time in this format: dd/MM/yyyy HH:mm");
    }
}
