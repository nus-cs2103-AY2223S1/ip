package ui;

public class Ui {

  /**
   * Outputs a line break to the ui to signify the end of a message.
   */
  private static void endMessage() {
    System.out.println("-".repeat(100));
  }

  /**
   * Outputs a message to the ui.
   *
   * @param message The message to be printed.
   */
  public static void print(String message) {
    System.out.println(message);
    endMessage();
  }
}
