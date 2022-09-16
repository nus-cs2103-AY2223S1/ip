package monkeexceptions;

public class NoKeywordException extends MonkeException {
    public NoKeywordException(String command) {
        super(command + " must be followed by a keyword. Please enter a keyword.");
    }
}
