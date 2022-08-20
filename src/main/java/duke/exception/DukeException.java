package duke.exception;

public class DukeException extends Exception {

  public DukeException() {
    super("Sorry, something went wrong!");
  }

  public DukeException(String message) {
    super(message);
  }
}
