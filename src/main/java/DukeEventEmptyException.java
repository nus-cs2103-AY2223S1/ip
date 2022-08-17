public class DukeEventEmptyException extends DukeException{
     DukeEventEmptyException() {
        super();
    }
    public String toString() {
        return "OOPS!!! The description of an event cannot be empty.";
    }

}
