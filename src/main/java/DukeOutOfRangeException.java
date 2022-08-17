public class DukeOutOfRangeException extends DukeException {

    public DukeOutOfRangeException(int range) {
        super("Task not found! Your number should be from 0 to " + range);
    }

}
