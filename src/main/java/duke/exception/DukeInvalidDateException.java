package duke.exception;

/**
 * Thrown when Duke encounters an invalid date format.
 *
 * @author Lim Ai Lin
 */

public class DukeInvalidDateException extends DukeException {

   /**
    * Creates a DukeInvalidDateException.
    */
   public DukeInvalidDateException() {
       super("RAWR! Please enter date in the format: dd/M/yyyy");
   }
}
