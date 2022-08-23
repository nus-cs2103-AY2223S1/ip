package duke;
public class UnknownInputException extends DukeException {
    public UnknownInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
