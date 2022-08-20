package duke;

import duke.exception.DukeException;
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
    while (true) {
      ui.showInput();
      try {
        boolean shouldExit = Parser.readCommand(ui, taskList);
        if (shouldExit) {
          ui.showGoodbye();
          storage.save(taskList.getTaskList());
          break;
        }
      } catch (DukeException e) {
        ui.showError(e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    new Duke(FILE_PATH).run();
  }
}
