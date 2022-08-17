public class MultipleTasksException extends DukeException {

    public MultipleTasksException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.toString() + "You can't input multiple tasks at one go.";
    }
}
