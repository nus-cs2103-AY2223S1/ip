package alanExceptions;

public class NoKeywordException extends AlanException {
    public NoKeywordException(String command) {
        super(command + " must be followed by a keyword. Please enter a keyword.");
    }
}
