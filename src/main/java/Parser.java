public class Parser {

  public static Command parse(String fullCommand) throws CheeseException {
    String[] fullCommandArray = fullCommand.split(" ", 2);
    String command = fullCommandArray[0];
    try {
      switch (command) {
        case "bye":
          validateCommandHasNArguments(fullCommandArray, 0);
          return new ByeCommand();
        case "list":
          validateCommandHasNArguments(fullCommandArray, 0);
          return new ListCommand();
        case "mark":
          validateCommandHasNArguments(fullCommandArray, 1);
          String markCommandArgument = fullCommandArray[1];
          return new MarkCommand(parseArgumentToInt(markCommandArgument));
        case "unmark":
          validateCommandHasNArguments(fullCommandArray, 1);
          String unmarkCommandArgument = fullCommandArray[1];
          return new UnmarkCommand(parseArgumentToInt(unmarkCommandArgument));
        case "delete":
          validateCommandHasNArguments(fullCommandArray, 1);
          String deleteCommandArgument = fullCommandArray[1];
          return new DeleteCommand(parseArgumentToInt(deleteCommandArgument));
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
  private static void validateCommandHasNArguments(String[] fullCommandArray, int n) throws CheeseException {
    if (fullCommandArray.length != n + 1) {
      throw new CheeseException();
    }
  }

  private static int parseArgumentToInt(String argument) throws CheeseException {
    int parsedArgument;
    try {
      parsedArgument = Integer.parseInt(argument);
    } catch (NumberFormatException e) {
      throw new CheeseException("Cannot convert non-integer to integer.");
    }
    return parsedArgument;
  }

  private static Task getTaskFromList(String[] inputArray) throws CheeseException, NumberFormatException {
    if (inputArray.length == 1 || inputArray[1].length() == 0) {
      throw new CheeseException("Sowwy, the item number cannot be empty.");
    }
    int itemIdx = Integer.parseInt(inputArray[1]) - 1;
    if (itemIdx < 0 || itemIdx >= Cheese.list.size()) {
      throw new CheeseException("Item number is not in list range.");
    }
    return Cheese.list.get(itemIdx);
  }
}
