package alpha;

/** Throws exceptions */
public class AlphaException extends Exception {

    /**
    * Constructor to initialise the Exception message.
    *
    * @param message To initialise the Exception message.
    */
    public AlphaException(String message) {
        super(message);
    }

    /**
    * {@inheritDoc}
    *
    * Checks the equality of two objects.
    * Returns true if both objects are instance of AlphaException class and have the same Exception message.
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof AlphaException) {
            AlphaException a = (AlphaException) obj;
            return (a.getMessage().equals(this.getMessage()));
        }
        return false;
    }
}
