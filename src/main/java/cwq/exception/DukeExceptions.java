package cwq.exception;

/**
 * DukeExceptions is the super class of all exceptions in Duke system
 */
public class DukeExceptions extends Exception{
    /**
     * Constructor for DukeException.
     * @param msg exception message
     */
    public DukeExceptions(String msg) {
        super(msg);
    }
}
