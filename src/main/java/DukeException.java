public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        String message = super.getMessage();
        return message;
    }
}
