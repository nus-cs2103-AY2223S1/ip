package duke;

import duke.command.Command;
import duke.common.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

  private static final String FILE_PATH = "data/tasks.txt";

  private Ui ui;
  private Storage storage;
  private TaskList taskList;

  Duke(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      taskList = new TaskList(storage.load());
    } catch (DukeException e) {
      ui.showError(e.getMessage());
      taskList = new TaskList();
    }
  }

  void run() {
    ui.showGreeting();
    boolean isExit = false;
    while (!isExit) {
      ui.showInput();
      try {
        String fullCommand = Parser.readCommand();
        ui.showLine();
        Command command = Parser.parse(fullCommand);
        command.execute(taskList, ui, storage);
        isExit = command.isExit();
      } catch (DukeException e) {
        ui.showError(e.getMessage());
      } finally {
        ui.showLine();
      }
    }
  }

  public static void main(String[] args) {
    new Duke(FILE_PATH).run();
  }
}
