package duke.models.serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;

/**
 * Encapsulates the logic for serializing and deserializing a particular object of type {@code T}.
 *
 * @author Emily Ong Hui Qi
 * @param <T> The type of the object that can be serialized and deserialized
 *
 */
public abstract class Serializable<T> {
    private static final String ERROR_MATCHING_SERIALIZABLE_REGEX = "Regex for serialized string does not match!";

    /**
     * Use the double slash to escape the pipe character.
     */
    private static final String formatterRead = " \\| ";
    private static final String formatterWrite = formatterRead.replace("\\", "");

    private final String serialized;
    private final String[] originalData;

    /**
     * Stores the original data and sets up a serialized form of the data.
     *
     * @param originalData The original data.
     */
    public Serializable(String[] originalData) {
        this.originalData = originalData;
        this.serialized = String.join(Serializable.formatterWrite, originalData);
    }

    /**
     * Initializes a new {@code Serializable} object by applying the reverse of the serialization operation on the
     * serialized string to obtain the original data as well as storing the serialized string, if and only if the
     * provided serialized string matches the expected format.
     *
     * @param serializedString The received serialized string.
     * @param regexMatch       The Regex format that the serialized string should match.
     *
     * @throws DukeException If the received serialized string does not match the provided Regex format.
     */
    public Serializable(String serializedString, Pattern regexMatch) throws DukeException {
        Matcher matcher = regexMatch.matcher(serializedString);
        if (!matcher.matches()) {
            throw new DukeException(Serializable.ERROR_MATCHING_SERIALIZABLE_REGEX);
        }
        this.serialized = serializedString;
        this.originalData = serializedString.split(Serializable.formatterRead);
    }

    /**
     * Supports the deserialization operation of the serializable object.
     *
     * @return The deserialized object.
     * @throws DukeException If the object cannot be deserialized.
     */
    public abstract T deserialize() throws DukeException;

    /**
     * Returns the original data passed into the {@code Serializable} constructor.
     *
     * @return Original data passed into the constructor.
     */
    protected String[] get() {
        return this.originalData;
    }

    @Override
    public String toString() {
        return this.serialized;
    }
}
