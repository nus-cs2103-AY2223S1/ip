public class IllegalCommandException extends DukeException {

    public IllegalCommandException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "Invalid " + super.getMessage() + " format.";
    }

}
