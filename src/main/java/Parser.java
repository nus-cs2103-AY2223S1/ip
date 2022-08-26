public class Parser {

  public static Command parse(String fullCommand) throws CheeseException {
    String[] fullCommandArray = fullCommand.split(" ", 2);
    String command = fullCommandArray[0];
    switch (command) {
      case "bye":
        validateCommandHasNArguments(fullCommandArray, 0);
        return new ByeCommand();
      case "list":
        validateCommandHasNArguments(fullCommandArray, 0);
        return new ListCommand();
      case "mark":
        validateCommandHasNArguments(fullCommandArray, 1);
        String markArgument = fullCommandArray[1];
        return new MarkCommand(parseArgumentToInt(markArgument));
      case "unmark":
        validateCommandHasNArguments(fullCommandArray, 1);
        String unmarkArgument = fullCommandArray[1];
        return new UnmarkCommand(parseArgumentToInt(unmarkArgument));
      case "delete":
        validateCommandHasNArguments(fullCommandArray, 1);
        String deleteArgument = fullCommandArray[1];
        return new DeleteCommand(parseArgumentToInt(deleteArgument));
      case "todo":
        validateCommandHasNArguments(fullCommandArray, 1);
        String todoArgument = fullCommandArray[1];
        return new TodoCommand(todoArgument);
      case "deadline":
        return parseDeadlineCommand(fullCommandArray);
      case "event":
        return parseEventCommand(fullCommandArray);
      default:
        return new UnknownCommand();
    }
  }

  private static Command parseDeadlineCommand(String[] fullCommandArray) throws CheeseException {
    validateCommandHasNArguments(fullCommandArray, 1);
    String deadlineArgument = fullCommandArray[1];
    if (deadlineArgument.indexOf("/by") == -1) {
      throw new CheeseException("A deadline requires a /by flag.");
    }
    String[] deadlineArgumentArray = deadlineArgument.split("/by", 2);
    if (deadlineArgumentArray[0].length() == 0 || deadlineArgumentArray[1].length() == 0) {
      throw new CheeseException("A deadline requires both a description and deadline.");
    }
    String description = deadlineArgumentArray[0];
    String deadline = deadlineArgumentArray[1];
    return new DeadlineCommand(description, deadline);
  }

  private static Command parseEventCommand(String[] fullCommandArray) throws CheeseException {
    validateCommandHasNArguments(fullCommandArray, 1);
    String eventArgument = fullCommandArray[1];
    if (eventArgument.indexOf("/at") == -1) {
      throw new CheeseException("A deadline requires an /at flag.");
    }
    String[] eventArgumentArray = eventArgument.split("/at", 2);
    if (eventArgumentArray[0].length() == 0 || eventArgumentArray[1].length() == 0) {
      throw new CheeseException("An event requires both a description and event time.");
    }
    String description = eventArgumentArray[0];
    String timeInterval = eventArgumentArray[1];
    return new DeadlineCommand(description, timeInterval);
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
}
