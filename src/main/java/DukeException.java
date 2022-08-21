public class DukeException extends Exception {

    public DukeException(String message) {
         super("    ____________________________________________________________\n"
                + "    " + message
                + "    ____________________________________________________________\n");
    }
}
