public class DukeException extends Exception {

    public DukeException(String message) {
         super("\n" +
                 "    ____________________________________________________________\n"
                + "    " + message +"\n"
                + "    ____________________________________________________________\n");
    }
}
