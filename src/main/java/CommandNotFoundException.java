public class CommandNotFoundException extends DukeException {

    public CommandNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "Command not found: " + super.getMessage();
    }

}
