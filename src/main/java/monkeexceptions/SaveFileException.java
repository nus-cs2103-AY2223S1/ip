package monkeExceptions;

public class SaveFileException extends MonkeException {
    public SaveFileException() {
        super("Save file cannot be located or created.");
    }
}
