package duke.exception;

/**
 * Invalid input exception when user enter invalid command.
 */
public class InvalidInputException extends DukeException{

    /**
     * Constructor for invalid input exception.
     */
    public InvalidInputException(){
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
