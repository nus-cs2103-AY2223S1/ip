public class IllegalDateTimeException extends DukeException {

    public IllegalDateTimeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "Invalid date/time format: " + super.getMessage();
    }

}