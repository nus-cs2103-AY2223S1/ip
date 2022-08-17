@FunctionalInterface
public interface DukeParser<T> {
    T parse(String input) throws DukeException;
}
