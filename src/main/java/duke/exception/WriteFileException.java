package duke.exception;

import java.nio.file.Path;

/**
 * The DukeRuntimeException that cannot write file correctly.
 */
public class WriteFileException extends DukeRuntimeException {
    /**
     * The constructor of the Exception.
     * @param path The path of the file.
     * @param message The error detail.
     */
    public WriteFileException(Path path, String message) {
        super("When writing '" + path.toString() + "'." + ":\n" + message);
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
        if (obj instanceof WriteFileException) {
            WriteFileException e = (WriteFileException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
