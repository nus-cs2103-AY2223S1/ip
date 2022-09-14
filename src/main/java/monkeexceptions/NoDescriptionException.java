package monkeexceptions;

public class NoDescriptionException extends MonkeException {
    public NoDescriptionException(String command) {
        super(command + " must be followed by a description, please enter a description.");
    }
}
