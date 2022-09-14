package monkeexceptions;

public class RemovingDefaultKeywordException extends MonkeException {
    public RemovingDefaultKeywordException() {
        super("You cannot remove default keywords.");
    }
}
