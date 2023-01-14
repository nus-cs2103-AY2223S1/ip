package amanda.exception;

public class EmptyDateException extends AmandaException {

	public EmptyDateException() {
		super("Please enter a date. Do you not want to get this done?");
	}
}
