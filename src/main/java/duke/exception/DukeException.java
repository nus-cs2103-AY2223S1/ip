package duke.exception;

/**
 * Handles exceptions for the Duke programme.
 */
public class DukeException extends Exception {

  /**
   * Initialises a DukeException.
   *
   * @param message The message to be printed when the Exception is thrown.
   */
  public DukeException(String message) {
    super(message);
  }

  /**
   * The string representation of a DukeException.
   */
  @Override
  public String toString() {
    return "Hunbun, ERROR!!! " + getMessage();
  }
}
