package exceptions;

public class EmptyDescriptionException extends DukeException {

    private final String task;

    public EmptyDescriptionException(String task) {
        this.task = task;
    }

    @Override
    public String getMessage() {
        return "Description of a " + this.task + " cannot be empty!";
    }
}
