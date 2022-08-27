public class DukeInvalidDateException extends DukeException {
    public DukeInvalidDateException() {
        super("It seems like you are using an invalid date format. Please input your date in the format" +
                "yyyy-mm-dd");
    }
}
