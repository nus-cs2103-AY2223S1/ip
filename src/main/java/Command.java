import java.util.Scanner;

/*
 * Enum class used to store the command flags and execution logic of the various availabe commands.
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public enum Command {
  /** Command to list all available Tasks*/
  list,
  /** Command to create a new Todo*/
  todo,
  /** Command to create a new Deadline*/
  deadline,
  /** Command to create a new Event*/
  event,
  /** Command to exit the system*/
  bye,
  /** Command to delete a Task*/
  delete,
  /** Command to mark a task as complete*/
  mark,
  /** Command to mark a task as incomplete*/
  unmark;

  private static final String LOGO =
    " ____        _        \n" +
    "|  _ \\ _   _| | _____ \n" +
    "| | | | | | | |/ / _ \\\n" +
    "| |_| | |_| |   <  __/\n" +
    "|____/ \\__,_|_|\\_\\___|\n";
  private static final String BORDER = "------------------------------";
  private static boolean isChatting = true;
  private static DukeException noNumberException = new DukeException(
    "Sorry, no number was detected"
  );

  /**
   * Execute the greet command and welcome the user.
   */
  public static void greet() {
    System.out.println("Hello from\n" + Command.LOGO);
    System.out.println("What can I do for you?");
  }

  /**
   * Execute the exit command and end the program.
   *
   * @param scanner  The scanner object that needs to be closed
   */
  public static void exit(Scanner scanner) {
    System.out.println(" Bye. Hope to see you again soon!");
    Command.isChatting = false;
    scanner.close();
  }

  /**
   * Execute the command to list all available tasks.
   *
   * @param allTasks  the list to store all tasks created by the user
   */
  public static void listTasks(AllTasksList allTasks) {
    allTasks.listAllTasks();
  }

  /**
   * Execute the command to chat with the user.
   *
   * @param scanner  the scanner object to get user input
   * @param allTasks  the list to store all tasks created by the user
   */
  public static void chat(Scanner scanner, AllTasksList allTasks) {
    while (isChatting) {
      try {
        System.out.println(BORDER);
        System.out.print(": ");

        String userInput = scanner.nextLine();
        Command.parseAndExecuteCommand(userInput, scanner, allTasks);

        System.out.println(BORDER + "\n");
      } catch (DukeException e) {
        System.out.println(e.getMessage());
      } finally {
        System.out.println("Try Again (:");
      }
    }
  }

  /**
   * Execute the command to mark a task as complete.
   *
   * @param commandArray  a string array of commands to be parsed for more information
   * @param allTasks  the list to store all tasks created by the user
   * @throws DukeException
   */
  public static void markTask(String[] commandArray, AllTasksList allTasks)
    throws DukeException {
    try {
      if (commandArray.length <= 1) {
        throw noNumberException;
      }

      int index = Integer.parseInt(commandArray[1]);
      allTasks.markTask(index);
    } catch (NumberFormatException e) {
      throw noNumberException;
    }
  }

  /**
   * Execute the command to mark a task as incomplete.
   *
   * @param commandArray  a string array of commands to be parsed for more information
   * @param allTasks  the list to store all tasks created by the user
   * @throws DukeException
   */
  public static void unMarkTask(String[] commandArray, AllTasksList allTasks)
    throws DukeException {
    try {
      if (commandArray.length <= 1) {
        throw noNumberException;
      }

      int index = Integer.parseInt(commandArray[1]);
      allTasks.unMarkTask(index);
    } catch (NumberFormatException e) {
      throw noNumberException;
    }
  }

  /**
   * Execute the command to delete a task.
   *
   * @param commandArray  a string array of commands to be parsed for more information
   * @param allTasks  the list to store all tasks created by the user
   * @throws DukeException
   */
  public static void delete(String[] commandArray, AllTasksList allTasks)
    throws DukeException {
    try {
      if (commandArray.length <= 1) {
        throw noNumberException;
      }

      int index = Integer.parseInt(commandArray[1]);
      allTasks.delete(index);
    } catch (NumberFormatException e) {
      throw noNumberException;
    }
  }

  /**
   * utility method used to parse the and execute the user command.
   *
   * @param userInput  the raw input string the user entered into the chatbot
   * @param scanner  the scanner that needs to be closed
   * @param allTasks  the Object storing all the tasks created by the user
   * @throws DukeException
   * @throws NumberFormatException
   */
  public static void parseAndExecuteCommand(
    String userInput,
    Scanner scanner,
    AllTasksList allTasks
  )
    throws DukeException {
    String[] commandArray = userInput.split(" ");
    String command = commandArray[0];

    try {
      switch (Command.valueOf(command)) {
        case bye:
          Command.exit(scanner);
          return;
        case list:
          Command.listTasks(allTasks);
          break;
        case mark:
          Command.markTask(commandArray, allTasks);
          break;
        case unmark:
          Command.unMarkTask(commandArray, allTasks);
          break;
        case delete:
          Command.delete(commandArray, allTasks);
          break;
        default:
          Task newTask = Task.createTask(commandArray);
          allTasks.addTask(newTask);
      }
    } catch (IllegalArgumentException e) {
      throw new DukeException();
    }
  }
}
