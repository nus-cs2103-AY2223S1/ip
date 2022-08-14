public class DukeException extends Exception{
  public DukeException(String message) {
    super(Output.wrapper("☹ OOPS!!! " + message + "\n"));
  }
}
