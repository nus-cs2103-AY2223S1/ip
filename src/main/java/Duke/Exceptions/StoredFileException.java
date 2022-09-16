package duke.exceptions;

/**
 * Class that denotes the Exception for the file storing data.
 */
public class StoredFileException extends DukeException{
    @Override
    public String toString() {
        return "Sorry, something wrong with your stored file...";
    }
}
