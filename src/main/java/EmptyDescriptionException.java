public class EmptyDescriptionException extends Exception{
    public EmptyDescriptionException(String task) {
        super(String.format("The description of this %s cannot be empty.", task));
    }
}
