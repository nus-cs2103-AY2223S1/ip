public class EmptyCommandException extends DukeException {

    public EmptyCommandException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "The " + super.getMessage() + " description cannot be empty.";
    }
}
