package amanda.exception;

public class InvalidDescriptionException extends AmandaException {

	public InvalidDescriptionException() {
		super("You tried to add a task without a description. Why are you like this?\n");
	}
}
