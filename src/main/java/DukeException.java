public class DukeException extends Exception {

  DukeException() {
    super("Sorry, something went wrong!");
  }

  DukeException(String message) {
    super(message);
  }
}
