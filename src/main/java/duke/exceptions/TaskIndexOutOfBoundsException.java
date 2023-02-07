package duke.exceptions;

/**
 * TaskIndexOutOfBoundsException class
 */
public class TaskIndexOutOfBoundsException extends RuntimeException {

    /**
     * TaskIndexOutOfBoundsException constructor method
     */
    public TaskIndexOutOfBoundsException() {
        super("Index is out of range");
    }

    /**
     * TaskIndexOutOfBoundsException constructor method using given string
     */
    public TaskIndexOutOfBoundsException(String s) {
        super(s);
    }

    /**
     * TaskIndexOutOfBoundsException constructor method using given integer index
     */
    public TaskIndexOutOfBoundsException(int index) {
        super("Index out of range: " + index);
    }

    /**
     * TaskIndexOutOfBoundsException constructor method using given long index
     */
    public TaskIndexOutOfBoundsException(long index) {
        super("Index out of range: " + index);
    }

}
