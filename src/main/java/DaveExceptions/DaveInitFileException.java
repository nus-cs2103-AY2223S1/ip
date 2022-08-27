package DaveExceptions;

public class DaveInitFileException extends DaveException {

    /**
     * Creates an exception for failing to initialize save files for Dave 2.
     */
    public DaveInitFileException() {
        super("Error with initialising save file!");
    }
}
