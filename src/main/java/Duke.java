import java.util.ArrayList;
import java.util.Scanner;

import command.ByeCommand;
import command.Command;
import command.DeleteCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TaskCommand;
import exception.DukeException;
import parse.Parser;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class Duke {

  /**
   * Stores all the tasks of the user.
   */
  private static TaskList tasks;

  /**
   * Handles the start up, running, and terminating of the Duke programme.
   */
  public static void main(String[] args) throws DukeException {
    // Welcome message
    Ui.print("MumBot: Hi dear! You are precious <3\n");

    /**
     * Handling of the .txt file containing the list of tasks.
     */
    String filePath = "../../../data/duke.txt";
    Storage storage = new Storage(filePath);
    tasks = storage.getTasks();

    String input;
    Scanner sc = new Scanner(System.in);
    input = Ui.awaitUserInput(sc);

    while (true) {
      boolean notTerminated;

      try {
        notTerminated = Parser.settleInput(input, tasks);
      } catch (DukeException e) {
        Ui.print(e + "\n");
        input = Ui.awaitUserInput(sc);
        continue;
      }

      if (notTerminated) {
        input = Ui.awaitUserInput(sc);
      } else {
        break;
      }
    }

    storage.save();
    System.exit(0);
  }
}
