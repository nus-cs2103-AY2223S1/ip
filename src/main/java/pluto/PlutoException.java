package pluto;

/**
 * Exception thrown by pluto.
 */
public class PlutoException extends Exception {
    /**
     * Exceptions created during execution of Pluto tasks and commands.
     * @param str Message in case of an exception.
     */
    public PlutoException(String str) {
        super(str);
    }
}
