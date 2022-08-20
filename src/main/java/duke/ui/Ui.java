package duke.ui;

public class Ui {

  private static final String LOGO =
    "______       _     \n" +
    "| ___ \\     | |    \n" +
    "| |_/ / ___ | |__  \n" +
    "| ___ \\/ _ \\| '_ \\ \n" +
    "| |_/ / (_) | |_) |\n" +
    "\\____/ \\___/|_.__/ \n";

  public void showGreeting() {
    System.out.println("Hello from");
    System.out.println(LOGO);
    System.out.println("What can I do for you?");
  }

  public void showGoodbye() {
    System.out.println("Goodbye!");
  }

  public void showInput() {
    System.out.print("> ");
  }

  public void showOutput(String message) {
    System.out.println(message);
  }

  public void showError(String error) {
    System.out.println("Error: " + error);
  }
}
