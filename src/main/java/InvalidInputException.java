public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("____________________________________________________________\n" +
                "I do not recognise this command :(\n" +
                "____________________________________________________________");
    }
}
