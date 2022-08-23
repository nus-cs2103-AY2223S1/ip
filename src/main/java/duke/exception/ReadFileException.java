package duke.exception;

import java.nio.file.Path;

public class ReadFileException extends RuntimeException {
    public ReadFileException(Path path, String message) {
        super("When visiting '" + path.toString() + "'." + ":\n" + message);
    }
}
