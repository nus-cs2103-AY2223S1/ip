package amanda.exception;

public class InvalidDateFormatException extends AmandaException {

	public InvalidDateFormatException() {
		super("Please enter the date and time in the correct format. Please don't waste my time!\n");
	}
}
