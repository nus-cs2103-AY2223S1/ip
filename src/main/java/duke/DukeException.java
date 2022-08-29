package duke;

/**
 * Exception class used to customise errors specific to Duke
 */
public class DukeException extends Exception {
   public DukeException (String s) {
       super(s);
   }
}
