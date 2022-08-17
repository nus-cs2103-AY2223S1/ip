public class DukeException extends Exception {

    DukeException(String message) {
        super("Uh-oh ☹! " + message);
    }

    DukeException(String message, Throwable cause) {
        super("Uh-oh ☹! " + message, cause);
    }

}
