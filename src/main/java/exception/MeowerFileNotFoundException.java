package exception;

/**
 * When file address given is not valid
 */
public class MeowerFileNotFoundException extends MeowerException {

    public MeowerFileNotFoundException(String message) {
        super(message);
    }
    
}
