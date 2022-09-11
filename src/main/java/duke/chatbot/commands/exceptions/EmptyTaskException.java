package duke.chatbot.commands.exceptions;

/**
 * Exception that is thrown when there is an empty task.
 */
public class EmptyTaskException extends Exception {
    public EmptyTaskException() {
        super("You cannot have an empty Task!\n");
    }
}
