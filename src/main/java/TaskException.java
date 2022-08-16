public class TaskException extends DukeException{
    public TaskException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "description cannot be empty please fill in something";
    }
}
