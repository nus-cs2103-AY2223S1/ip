package duke.models.serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;

/**
 * TODO: Add JavaDocs
 */
public abstract class Serializable<T> {
    private static final String ERROR_MATCHING_SERIALIZABLE_REGEX = "Regex for serialized string does not match!";

    // Use the double slash to escape the pipe character
    private static final String formatterRead = " \\| ";
    private static final String formatterWrite = formatterRead.replace("\\", "");
    private final String serialized;
    private final String[] originalData;

    /**
     * TODO: Add JavaDocs
     */
    public Serializable(String[] data) {
        this.originalData = data;
        this.serialized = String.join(Serializable.formatterWrite, data);
    }

    /**
     * TODO: Add JavaDocs
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
     * Returns the original data passed into the Serializable constructor ie the inverse
     * operation of serialization
     *
     * @return Original data passed into the constructor
     */
    protected String[] get() {
        return this.originalData;
    }

    @Override
    public String toString() {
        return this.serialized;
    }

    /**
     * Supports the deserialization operation of the serializable object
     *
     * @return The deserialized object
     */
    public abstract T deserialize() throws DukeException;
}
