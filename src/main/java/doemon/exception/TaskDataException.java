package doemon.exception;

/**
 * Exception thrown when reading or writing to data file fails.
 */
public class TaskDataException extends DoemonException {
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Sorry, my old piece of bread got mouldy..."
                + "\nBut I got you a brand new one!";
    }
}
