package duke.exception;

import java.nio.file.Path;

/**
 * The DukeRuntimeException that cannot read from file correctly.
 */
public class ReadFileException extends DukeRuntimeException {
    /**
     * Constructs ReadFileException.
     * @param path The path of the file.
     * @param message The error detail.
     */
    public ReadFileException(Path path, String message) {
        super("When visiting '" + path.toString() + "'." + ":\n" + message);
    }
}
