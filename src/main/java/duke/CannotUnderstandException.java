package duke;

/**
 * To handle some ghost like messages
 *
 * @author Lan Jingbo, Jerry
 */
public class CannotUnderstandException extends Exception {
    public CannotUnderstandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
