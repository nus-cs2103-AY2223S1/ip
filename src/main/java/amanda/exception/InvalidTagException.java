package amanda.exception;

public class InvalidTagException extends AmandaException {

	public InvalidTagException() {
		super("Wrong tag format. There should be no spaces in your tag and don't enter an empty tag!");
	}
}