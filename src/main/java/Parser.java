public class Parser {

  public static Command parse(String fullCommand) throws CheeseException {
    String[] fullCommandArray = fullCommand.split(" ", 2);
    String command = fullCommandArray[0];
    try {
      switch (command) {
        case "bye":
          validateCommandHasNoArguments(fullCommandArray);
          return new ByeCommand();
        case "list":
          validateCommandHasNoArguments(fullCommandArray);
          return new ListCommand();
        case mark:
          taskList.markTaskAsDone(Integer.parseInt(inputArray[1]) - 1);
          taskList.saveTaskList();
          break;
        case unmark:
          taskList.markTaskAsNotDone(Integer.parseInt(inputArray[1]) - 1);
          taskList.saveTaskList();
          break;
        case delete:
          taskList.deleteTask(Integer.parseInt(inputArray[1]) - 1);
          taskList.saveTaskList();
          break;
        default:
          Task task = Task.createTask(inputArray);
          taskList.addTask(task);
          taskList.saveTaskList();
      }
    } catch (NumberFormatException e) {
      System.out.println("Cannot convert non-integer to integer.");
    } catch (CheeseException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Checks if given command has no arguments
   * 
   * @param inputArray array containing user input after splitting by space
   * @throws CheeseException if given command contains extra arguments
   */
  private static void validateCommandHasNoArguments(String[] fullCommandArray) throws CheeseException {
    if (fullCommandArray.length != 1) {
      throw new CheeseException();
    }
  }
}
