package duke.exception;

import java.nio.file.Path;

/**
 * The DukeRuntimeException that cannot write file correctly.
 */
public class WriteFileException extends DukeRuntimeException {
    /**
     * Constructs WriteFileException.
     * @param path The path of the file.
     * @param message The error detail.
     */
    public WriteFileException(Path path, String message) {
        super("When writing '" + path.toString() + "'." + ":\n" + message);
    }
}
