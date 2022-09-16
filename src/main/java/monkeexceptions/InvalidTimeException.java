package monkeexceptions;

public class InvalidTimeException extends MonkeException {
    public InvalidTimeException() {
        super("You could entire the time in this format: dd/MM/yyyy HH:mm");
    }
}
