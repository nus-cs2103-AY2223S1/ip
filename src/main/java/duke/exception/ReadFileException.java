package duke.exception;

import java.nio.file.Path;

public class ReadFileException extends RuntimeException {
    public ReadFileException(Path path, String message) {
        super("When visiting '" + path.toString() + "'." + ":\n" + message);
    }

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
