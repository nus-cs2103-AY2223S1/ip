public class DukeException extends Exception{

    /**
     * A constructor that creates an instance of DukeException.
     *
     * @param message A message to be printed.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String str = "☹ OOPS!!!";
        return str;
    }
}
