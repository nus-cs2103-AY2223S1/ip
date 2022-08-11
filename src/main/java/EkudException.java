public class EkudException extends Exception {
  public EkudException(String errorMessage) {
    super(String.format("Error: %s", errorMessage));
  }
}