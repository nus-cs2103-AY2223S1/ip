package exceptions;

public class DukeUnknownCommandException extends DukeException{

    public DukeUnknownCommandException() {
        super("Sorry. I do not understand the entered command");
    }
}
