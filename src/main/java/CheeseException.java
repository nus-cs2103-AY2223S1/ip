public class CheeseException extends Exception {
  public CheeseException() {
    super("Sowwy, I don't understand");
  }

  public CheeseException(String message) {
    super(message);
  }
}