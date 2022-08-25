package duke.exception;

public class ReadAttributeException extends RuntimeException{
    public ReadAttributeException(String className, String formattedString, String message) {
        super("When reading from '" + formattedString + "' in class " + className + ":\n" + message);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReadAttributeException) {
            ReadAttributeException e = (ReadAttributeException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
