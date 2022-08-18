package main;

import commands.*;
import exception.DukeException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.*;

/**
 * Duke is our helper which manages the commands
 */
public class Duke {

  /** The tasklist keeps track of all the tasks added */
  private ArrayList<Task> TaskList = new ArrayList<>();
  /** isExit tells Duke whether to close */
  private boolean isExit = false;
  /** sc takes in user input infinitely until Duke is closed */
  private Scanner sc = new Scanner(System.in);

  /**
   * Greets user upon opening Duke and does commands until Duke is closed
   */
  void run() {
    try {
      this.greet();
      while (true) {
        if (isExit) {
          break;
        } else {
          getCommand(sc.nextLine());
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Calls the parser to check the commands and catches exceptions
   * @param input User input of commands
   */
  private void getCommand(String input) {
    try {
      Command c = Parser.parse(input);
      c.execute(TaskList);
      if (c instanceof ByeCommand) {
        this.isExit = true;
      }
      System.out.println('\n');
    } catch (DukeException e) {
      System.out.println(e.getMessage() + '\n');
    } catch (IllegalArgumentException e) {
      System.out.println("No such command found \n");
    }
  }

  /**
   * Prints out greeting when user just opened Duke
   */
  private void greet() {
    String logo =
      " ____        _        \n" +
      "|  _ \\ _   _| | _____ \n" +
      "| | | | | | | |/ / _ \\\n" +
      "| |_| | |_| |   <  __/\n" +
      "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
  }

  /**
   * Creates new Duke and run it
   * @param args
   */
  public static void main(String[] args) {
    Duke duke = new Duke();
    duke.run();
  }
}
