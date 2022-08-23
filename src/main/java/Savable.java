public interface Savable<T> {
    String toFormattedString();

    String read();

    T parse(String formattedString) throws DukeException.ReadAttributeException;

    void save();

    String getStoragePath();
}
