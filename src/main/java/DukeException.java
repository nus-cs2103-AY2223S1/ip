public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String str = "☹ OOPS!!!";
        return str;
    }
}
