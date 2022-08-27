public class DateTimeNotFoundException extends DukeException {
    public DateTimeNotFoundException(String command, String keyword) {
        super(command + " command expects a specified Date and Time after " + keyword + " keyword!");
    }
}
