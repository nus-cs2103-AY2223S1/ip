package components;

import java.util.Scanner;

/**
 * Represents Ui, or the user's interface.
 */
public class Ui {

  private String userInput;

  public Ui() {
    System.out.println("What can I do for you?");
  }

  /**
   * gets user input formatted into strings and then to be read.
   *
   * @param sc scanner object which contains the text.
   */
  public void getPrompt(Scanner sc) {
    String line = "";
    while (sc.hasNext()) {
      try {
        line = sc.nextLine();
        Parser.parseLine(line);
      } catch (DukeException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * shows error when there is one during loading.
   *
   *
   */
  public void showLoadingError() {
    System.out.println("duke.txt not found!");
  }

  /**
   * displays Exception that is thrown.
   *
   * @param e exception that is thrown.
   */
  public void showExceptionError(Exception e) {
    System.out.println(e.getMessage());
  }

}
