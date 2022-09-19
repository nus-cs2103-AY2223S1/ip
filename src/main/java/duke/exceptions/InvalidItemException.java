package duke.exceptions;

/**
 * Represents an InvalidItemException class which is inherited from the Exception class and
 * occurs when the user tries to access or modify an item that does not exist in the list
 */
public class InvalidItemException extends Exception{
    /**
     * Constructs an InvalidItemException with standard message
     */
    public InvalidItemException(){
        super("Alas! You ask me to paint outside the canvas..." +
                "choose an item number from 0 to your list length! ");
    }

    /**
     * Constructs an EmptyDateTimeException with custom message
     * @param msg String representing custom message
     */
    public InvalidItemException(String msg) {
        super(msg);
    }
}
