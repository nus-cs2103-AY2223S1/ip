package duke.main;

import duke.commands.Command;
import duke.exception.DukeException;

import duke.tasks.*;

/**
 * Duke is our helper which manages the commands
 */
public class Duke {

  /** The tasklist keeps track of all the tasks added */
  private TaskList tasks;

  private Ui ui;
  private Storage storage;

  public Duke(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      this.tasks = new TaskList(storage.load());
    } catch (DukeException e) {
      ui.showError(e.getMessage());
      ui.showLoadingError();
      tasks = new TaskList();
    }
  }

  void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        ui.showLine(); // show the divider line ("_______")
        Command c = Parser.parse(fullCommand);
        c.execute(tasks, ui, storage);
        isExit = c.isExit();
      } catch (IllegalArgumentException e) {
        System.out.println("Unknown command received...");
      } catch (DukeException e) {
        ui.showError(e.getMessage());
      } finally {
        ui.showLine();
      }
    }
  }

  /**
   * Creates new Duke and run it
   *
   * @param args NA
   */
  public static void main(String[] args) {

    Duke duke = new Duke("data/tasks.txt");
    duke.run();

  }
}
