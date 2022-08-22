import java.io.File;

public interface Savable<T> {
    String toFormattedString();

    T read(String formattedString) throws DukeException.ReadAttributeException;
}
