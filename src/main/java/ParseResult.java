public class ParseResult {
  public final boolean terminate;
  public final String message;
  public final boolean saveStorage;

  public ParseResult(boolean terminate, String message, boolean saveStorage) {
    this.terminate = terminate;
    this.message = message;
    this.saveStorage = saveStorage;
  }
}
