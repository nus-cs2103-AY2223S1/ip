package bobthebot.exceptions;

/**
 * Custom exception class for handling exceptions specific to BobTheBot.
 */
public class BobException extends Exception {
    public BobException(String errorMessage) {
        super(errorMessage);
    }
}
