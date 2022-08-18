public class InvalidFormatException extends DukeException{
    public InvalidFormatException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! Please enter the correct format (/by or /at)";
    }
}
