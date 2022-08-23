public interface Savable<T> {
    String toFormattedString();

    T parse(String formattedString) throws DukeException.ReadAttributeException;
}
