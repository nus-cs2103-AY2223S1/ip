package duke.util;

import duke.exception.ReadAttributeException;

/**
 * The Interface representing the given type is able to be saved in a file by Duke.
 * @param <T> The given type.
 */
public interface Savable<T> {
    /**
     * Returns the formatted string representation of the object.
     * @return The formatted string representation of the object.
     */
    String toFormattedString();

    /**
     * Returns the object of type T instantiated by parsing the
     * given formatted String.
     * @param formattedString The given formatted String.
     * @return Returns the instantiated object of type T.
     * @throws ReadAttributeException if given String is not in correct format.
     */
    T parse(String formattedString) throws ReadAttributeException;
}
