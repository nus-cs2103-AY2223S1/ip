import java.time.LocalDateTime;

/**
 * Deals with making sense of the user command
 */
public class Parser {
  public static Command parse(String command) throws DukeException {
    switch (command) {
      case "bye":
        return new ExitCommand();
      case "list":
        return new ListCommand();
      case "mark":
        return new MarkCommand();
      case "unmark":
        return new UnmarkCommand();
      case "deadline": 
      case "todo":
      case "event":
        return new AddCommand();
      case "delete":
        return new DeleteCommand();
      case "save":
        return new SaveCommand();
      case "load":
        return new LoadCommand();
      case "date":
        return new MatchCommand();
      default:
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
  }
  /**
   * Finds the String between first command and second command (if exist)
   * @param input User input
   * @param command First command
   * @return String between first command and second command (if exist)
   */
  public static String findFirstCommand(String input, String command) throws DukeException {
    int endOfCommand = input.indexOf("/");
    int beginIndex = input.indexOf(command) + command.length() + 1;
    if (beginIndex > input.length()) {
      throw new DukeException("The description of a " + command + " cannot be empty.");
    }
    return endOfCommand == -1
            ? input.substring(beginIndex)
            : input.substring(beginIndex, endOfCommand);
  }

  /**
   * Finds the String between second command (if exist) and end
   * @param input User input
   * @param command Second command
   * @return String between second command (if exist) and end
   */
  public static String findSecondCommand(String input, String command) {
    return input.contains(command) && !command.equals("")
            ? input.substring(input.indexOf(command) + command.length() + 1)
            : "";
  }

  /**
   * Parses the user input and creates a Task object
   * @param input User input
   * @param command Command that the user inputs
   * @param storageList StorageList object
   * @param secCommand Second command that the user inputs
   */
  public static void parseTask(String input, String command, StorageList storageList, String secCommand) {
    try {
      String desc = findFirstCommand(input, command);
      String secondCommand = findSecondCommand(input, secCommand);
      switch (command) {
        case "deadline":
          addTask(new Deadline(desc, secondCommand), storageList);
          break;
        case "event":
          addTask(new Event(desc, secondCommand), storageList);
          break;
        case "todo":
          addTask(new Todo(desc), storageList);
          break;
      }
      FileIO.save(storageList, "./default.txt");
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Adds a Task to the StorageList, print out the appropriate String
   * @param task Task to be added to the StorageList
   * @param storageList StorageList to be added to
   */
  private static void addTask(Task task, StorageList storageList) {
    storageList.add(task);
    Output.ADD.modifyTask(task, storageList);
  }

  /**
   * Finds the index of the Task that the user wants to mark/unmark/delete
   * @param input User input
   * @return Index of the Task
   */
  public static int getIndex(String input) {
    return Integer.parseInt(input.split(" ")[1]) - 1;
  }
}
