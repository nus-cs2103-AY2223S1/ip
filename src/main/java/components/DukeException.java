package components;

/**
 * An exception exclusive to Duke chatbot.
 */
public class DukeException extends Exception {
  public DukeException(String errorMessage) {
    super(errorMessage);
  }
}