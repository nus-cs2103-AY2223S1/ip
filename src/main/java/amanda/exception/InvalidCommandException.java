package amanda.exception;

public class InvalidCommandException extends AmandaException {

	public InvalidCommandException() {
		super("That's not even a command. I have no idea what you just said!\n");
	}
}
