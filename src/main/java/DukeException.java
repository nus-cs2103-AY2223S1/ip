public class DukeException extends Exception {

    public DukeException(String message) {
        super("Duke says: \"OOPS! " + message + "\" ☹");
    }

}