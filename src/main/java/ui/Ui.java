package ui;

public class Ui {

  /**
   * Prints a line break to the ui to signify the end of a message.
   */
  private static void endMessage() {
    System.out.println("-".repeat(100));
  }

  /**
   * Prints a message to the ui. Also prints a new line.
   *
   * @param message The message to be printed.
   */
  public static void println(String message) {
    System.out.println(message);
    endMessage();
  }

  /**
   * Prints a message to the ui. Does not print a new line.
   *
   * @param message The message to be printed.
   */
  public static void print(String message) {
    System.out.print(message);
    endMessage();
  }
}
