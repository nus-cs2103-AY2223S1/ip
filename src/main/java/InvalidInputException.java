public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("I do not recognise this command :(");
    }
}
