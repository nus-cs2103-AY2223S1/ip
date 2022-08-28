package Duke.exceptions;

public class DukeException extends Exception {

    public DukeException(String message) {
         super("    " + message +"\n");
    }
}
