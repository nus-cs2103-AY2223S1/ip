@FunctionalInterface
public interface TaskParser {
    Task get() throws DukeException;
}
