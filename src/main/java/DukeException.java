public class DukeException extends Exception {

  public DukeException(String description) {
    super("☹ OOPS!!! " + description);
  }
}
