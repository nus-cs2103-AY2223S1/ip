package Duke;

/**
 * Exception class that throws an exception when
 * illegal commands are entered
 */
public class IllegalCommandException extends DukeException{

    public IllegalCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(.");
    }
}
