package monkeexceptions;

public class FileReadException extends MonkeException {
    public FileReadException() {
        super("Unable to read from save file.");
    }
}
