public class EkudException extends Exception {
  /**
   * Constructor that instantiates a new EkudException
   * @param errorMessage - The message describing the error.
   */
  public EkudException(String errorMessage) {
    super(String.format("Error: %s", errorMessage));
  }
}