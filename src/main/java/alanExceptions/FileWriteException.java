package alanExceptions;

public class FileWriteException extends AlanException {
    public FileWriteException() {
        super("Unable to write to save file.");
    }
}
