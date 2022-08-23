package duke.exception;

public class ReadAttributeException extends RuntimeException{
    public ReadAttributeException(String className, String formattedString, String message) {
        super("When reading from '" + formattedString + "' in class " + className + ":\n" + message);
    }
}
