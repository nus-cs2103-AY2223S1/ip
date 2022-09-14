package Duke.Exceptions;

public class InvalidCommandException extends DukeException{
    @Override
    public String toString() {
        return "Sorry, I don't offer such service";
    }
}
