public class DukeException extends Exception {
  public DukeException(String error) {
    super("Hang on! " + error);
  }
}
