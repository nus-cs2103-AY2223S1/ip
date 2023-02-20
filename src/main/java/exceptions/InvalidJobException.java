package exceptions;

/**
 * InvalidJobException is thrown if there is no such job at all
 */
public class InvalidJobException extends Exception {

    public InvalidJobException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
