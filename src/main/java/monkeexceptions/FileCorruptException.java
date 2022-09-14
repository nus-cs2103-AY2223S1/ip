package monkeExceptions;

public class FileCorruptException extends MonkeException {
    public FileCorruptException() {
        super("Unable to load save file. File corrupted.");
    }
}
