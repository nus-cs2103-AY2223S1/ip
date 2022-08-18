public class DukeException extends Exception {
  public DukeException(String error) {
    super("\033[0;31mHang On! " + error + "\033[0m");
  }
}
