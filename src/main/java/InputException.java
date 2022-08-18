public class InputException extends DukeException{
    public InputException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "Invalid input, please use only:\ntodo, deadline, event with a task";
    }
}
