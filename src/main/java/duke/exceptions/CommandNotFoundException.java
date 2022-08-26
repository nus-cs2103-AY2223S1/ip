package duke.exceptions;

public class CommandNotFoundException extends DukeException{

    public CommandNotFoundException(String message) {
        super(message + " DOES NOT EXISTS");
    }

}
