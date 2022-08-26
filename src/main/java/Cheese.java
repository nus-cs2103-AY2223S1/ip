public class Cheese {
  private TaskList taskList;
  private Storage storage;
  private Ui ui;

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

  public static void main(String[] args) {
    new Cheese("data/cheese.txt").run();
  }
}
