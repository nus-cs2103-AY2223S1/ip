package duke.exception;

import java.nio.file.Path;

public class WriteFileException extends RuntimeException {
    public WriteFileException(Path path, String message) {
        super("When writing '" + path.toString() + "'." + ":\n" + message);
    }
}
