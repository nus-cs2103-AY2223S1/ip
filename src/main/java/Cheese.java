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

  /**
   * Gets task from list
   * 
   * @param inputArray array containing user input after splitting by space
   * @return specified task from list
   * @throws CheeseException       if given item number is empty or item number is
   *                               not in range
   * @throws NumberFormatException if given item number is not in integer format
   */
  // private static Task getTaskFromList(String[] inputArray) throws
  // CheeseException, NumberFormatException {
  // if (inputArray.length == 1 || inputArray[1].length() == 0) {
  // throw new CheeseException("Sowwy, the item number cannot be empty.");
  // }
  // int itemIdx = Integer.parseInt(inputArray[1]) - 1;
  // if (itemIdx < 0 || itemIdx >= Cheese.list.size()) {
  // throw new CheeseException("Item number is not in list range.");
  // }
  // return Cheese.list.get(itemIdx);
  // }
}
