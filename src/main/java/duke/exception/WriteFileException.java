package duke.exception;

import java.nio.file.Path;

public class WriteFileException extends RuntimeException {
    public WriteFileException(Path path, String message) {
        super("When writing '" + path.toString() + "'." + ":\n" + message);
    }

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
