package alanExceptions;

public class RemovingDefaultKeywordException extends AlanException {
    public RemovingDefaultKeywordException() {
        super("You cannot remove default keywords.");
    }
}
