public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(int index) {
        super(String.format("Task at index %d cannot be found", index));
    }
}
