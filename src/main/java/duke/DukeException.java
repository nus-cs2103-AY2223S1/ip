package duke;

public class DukeException extends RuntimeException{

    private String exception = "";


    /**
     * Creates a new DukeException.
     * @param e Text to be displayed.
     */
    public DukeException(String e) {
        exception = e;
    }

    @Override
    public String toString() {
        return exception;
    }
}
