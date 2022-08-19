public class ParsingTaskException extends Exception {
    public ParsingTaskException(String addtionalMessage) {
        super("An error occurred parsing task data!\n" + addtionalMessage);
    }
}
