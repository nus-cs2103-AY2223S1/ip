package amanda.exception;

public class InvalidIndexException extends AmandaException {

	public InvalidIndexException() {
		super("Try inputting an index that actually exist. Thank you.\n");
	}
}
