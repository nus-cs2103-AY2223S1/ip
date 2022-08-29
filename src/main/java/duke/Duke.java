package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Represents Duke that converses with a user.
 */
public class Duke {
  private TaskList taskList;
  private Storage storage;
  private Ui ui;
  private Parser parser;

  /**
   * Initialises the Duke object.
   *
   * @param filePath Path to the save file.
   */
  public Duke(String filePath) {
    storage = new Storage(filePath);
    ui = new Ui();
    try {
      taskList = storage.readFile();
    } catch (DukeException e) {
      e.printStackTrace();
    }
    parser = new Parser(ui, storage);
  }

  public String getResponse(String input) {
    Command command = parser.parse(input);
    String result = command.execute(taskList);
    if (command instanceof ByeCommand) {
      Platform.exit();
    }
    return result;
  }
}
