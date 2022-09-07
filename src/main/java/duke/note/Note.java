package duke.note;

import duke.exception.DukeInvalidSaveDataException;

/**
 * Represents a note. Stores only a description.
 */
public class Note {
    private static final char DEFAULT_TAG = 'N';

    private String description;

    public Note(String description) {
        this.description = description;
    }

    /**
     * Converts save string data to a Note object.
     * The save string data is in the format:
     * <p>
     * <pre>
     * N,"&lt;description&gt;"
     * </pre>
     * <p>
     *
     * @param saveString
     * @return the new Note object created from saveString
     */
    public static Note fromSaveString(String saveString) throws DukeInvalidSaveDataException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if (splitSaveString.length != 2) {
            throw new DukeInvalidSaveDataException();
        }
        assert splitSaveString[0].startsWith("N") : "Save data is not a note.";
        String description = splitSaveString[1];
        return new Note(description);
    }

    /**
     * Converts Note into save data string to be stored in a file.
     * @return the save data string
     */
    public String toSaveData() {
        return String.format("%s,\"%s\"", DEFAULT_TAG, description);
    }

    /**
     * Returns the description of the note.
     * @param toFind
     * @return
     */
    public boolean descriptionContains(String toFind) {
        return description.contains(toFind);
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Note) {
            Note other = (Note) obj;
            return description.equals(other.description);
        }
        return false;
    }
}
