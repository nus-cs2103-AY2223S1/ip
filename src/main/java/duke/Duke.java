package duke;

import duke.task.TaskList;

/**
 * Represents Duke that converses with a user.
 */
class Duke {
  private static final String FILEPATH = "./data/duke.txt";
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
    parser = new Parser(taskList, ui);
  }

  private void run() {
    ui.greet();

    String input = "";
    boolean continueToRun = true;

    while (continueToRun) {
      input = ui.readCommand();
      if (input == null || input.equals("bye")) {
        continueToRun = false;
      } else {
        parser.parse(input);
      }
    }

    try {
      storage.saveFile(taskList);
    } catch (DukeException e) {
      e.printStackTrace();
    }
    ui.exitMessage();
  }

  /**
   * Main function of the application.
   */
  public static void main(String[] args) {
    new Duke(FILEPATH).run();
  }
}
