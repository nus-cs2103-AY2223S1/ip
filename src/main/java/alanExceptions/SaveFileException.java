package alanExceptions;

public class SaveFileException extends AlanException {
    public SaveFileException() {
        super("Save file cannot be located or created.");
    }
}
