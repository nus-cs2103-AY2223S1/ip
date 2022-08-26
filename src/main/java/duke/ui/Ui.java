package duke.ui;

import duke.exception.DukeException;

import java.util.Scanner;

public class Ui {
  private final Scanner s;
  private static final String DIVIDER =
      "\n-----------------------------------------------";

  public Ui() {
    s = new Scanner(System.in);
  }

  public void showIntro() {
    String intro = "Welcome to Apollo!\n" +
        "How can I help you today?";
    System.out.println(intro + DIVIDER);
  }

  public void showError(DukeException e) {
    System.out.println(e.getMessage() + DIVIDER);
  }

  public void showOutro() {
    System.out.println("Goodbye, see you soon!" + DIVIDER);
  }

  public void showToUser(String s) {
    System.out.println(s + DIVIDER);
  }

  public String getUserInput() {
    return s.nextLine();
  }
}
