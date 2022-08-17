public class TaskNotFoundException extends DukeException {

    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "Task " + super.getMessage() + " does not exist.";
    }
}
