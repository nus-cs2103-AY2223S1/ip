public class DukeException extends Exception {

  public DukeException(String msg) {
    super("\t-----------------------------------------------"
        + "\n\t" + msg
        + "\n\t-----------------------------------------------");
  }
}
