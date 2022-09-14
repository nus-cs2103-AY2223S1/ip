package monkeExceptions;

public class FileWriteException extends MonkeException {
    public FileWriteException() {
        super("Unable to write to save file.");
    }
}
