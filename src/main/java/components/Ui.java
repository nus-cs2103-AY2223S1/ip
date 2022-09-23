package components;

import java.util.Scanner;

/**
 * Represents Ui, or the user's interface.
 */
public class Ui {

  private String userInput;

  public Ui() { }

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
