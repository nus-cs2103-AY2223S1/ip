public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException() {
        super("____________________________________________________________\n" +
                "Oh no! This command requires a description input!\n" +
                "____________________________________________________________");
    }
}
