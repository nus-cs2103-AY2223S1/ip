package KKBot.storage.exceptions;

import KKBot.exceptions.KKBotException;

/**
 * An exception thrown when there is an error reading a file from the hard drive/ writing a file to hard drive.
 *
 * @author AkkFiros
 */
public class StorageException extends KKBotException {
    /**
     * Constructor for an StorageException.
     */
    public StorageException(String message) {
        super(message);
    }
}
