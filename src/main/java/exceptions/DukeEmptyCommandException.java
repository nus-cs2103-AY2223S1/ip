package exceptions;

public class DukeEmptyCommandException extends DukeException{

    public DukeEmptyCommandException() {
        super("I could not understand the instruction. There seems to be missing command");
    }
}
