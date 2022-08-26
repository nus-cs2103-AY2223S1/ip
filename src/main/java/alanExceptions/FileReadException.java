package alanExceptions;

public class FileReadException extends AlanException {
    public FileReadException() {
        super("Unable to read from save file.");
    }
}
