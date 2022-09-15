package duke.exception;

import java.nio.file.Path;

/**
 * The DukeRuntimeException that cannot read from file correctly.
 */
public class ReadFileException extends DukeRuntimeException {
    /**
     * The constructor of the Exception.
     * @param path The path of the file.
     * @param message The error detail.
     */
    public ReadFileException(Path path, String message) {
        super("When visiting '" + path.toString() + "'." + ":\n" + message);
    }

    /**
     * Returns boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReadFileException) {
            ReadFileException e = (ReadFileException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
