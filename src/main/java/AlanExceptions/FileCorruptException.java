package AlanExceptions;

public class FileCorruptException extends AlanException {
    public FileCorruptException() {
        super("Unable to load save file. File corrupted.");
    }
}
