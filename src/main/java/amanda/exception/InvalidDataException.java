package amanda.exception;

public class InvalidDataException extends AmandaException {

	public InvalidDataException() {
		super("I can't read your storage file, it's all messed up! Stop wasting my precious time.");
	}
}
