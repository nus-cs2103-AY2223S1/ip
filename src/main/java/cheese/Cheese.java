package cheese;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.parser.Parser;
import cheese.exception.CheeseException;
import cheese.command.Command;
import cheese.command.ByeCommand;

/**
 * Represents a command line interface used to manage tasks.
 */
public class Cheese {
  /** List of tasks. */
  private TaskList taskList;

  /** Storage to interact with save file. */
  private Storage storage;

  /** User interface to interact with user. */
  private Ui ui;

  /**
   * Constructs an instance of <code>Cheese</code>.
   * 
   * @param filePath File path of save file.
   */
  public Cheese(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      taskList = storage.load();
    } catch (CheeseException e) {
      ui.showError(e.getMessage());
      taskList = new TaskList();
    }
  }

  /**
   * Runs program until user enters command to exit.
   */
  public void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        Command command = Parser.parse(fullCommand);
        command.execute(taskList, storage, ui);
        isExit = ByeCommand.isBye(command);
      } catch (CheeseException e) {
        ui.showError(e.getMessage());
      }
    }
  }

  /**
   * Acts as entry point for program.
   * 
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    new Cheese("data/cheese.txt").run();
  }
}
