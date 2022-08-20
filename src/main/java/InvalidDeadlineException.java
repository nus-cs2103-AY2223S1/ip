public class InvalidDeadlineException extends InvalidCommandException {
    public InvalidDeadlineException() {
        super("Could not parse deadline. To create a deadline, "
                + "please use the format in this example: "
                + "deadline return book /by yyyy-MM-dd");
    }
}

