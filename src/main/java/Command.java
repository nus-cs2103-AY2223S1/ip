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

  /**
   * Execute the greet command and welcome the user.
   */
  public void greet() {
    System.out.println("Hello from\n" + Command.LOGO);
    System.out.println("What can I do for you?");
  }

  /**
   * Execute the exit command and end the program.
   *
   * @param scanner  The scanner object that needs to be closed
   */
  public void exit(Scanner scanner) {
    System.out.println(" Bye. Hope to see you again soon!");
    scanner.close();
  }

  /**
   * Execute the command to list all available tasks.
   */
  public void listTasks() {
    String output = "";
    for (int i = 0; i < Duke.allTasks.size(); i++) {
      output += "\n" + (i + 1) + ". " + Duke.allTasks.get(i).toString();
    }
    System.out.println(output);
  }

  /**
   * Execute the command to chat with the user.
   */
  public void chat(Scanner scanner) {
    System.out.println(BORDER);
    System.out.print(": ");

    String userInput = scanner.nextLine();
    parseAndExecuteCommand(userInput);

    System.out.println(BORDER + "\n");
  }

  /**
   * Execute the command to mark a task as complete.
   *
   * @param commandArray  a string array of commands to be parsed for more information
   */
  public void markTask(String[] commandArray) {}

  /**
   * Execute the command to mark a task as incomplete.
   *
   * @param commandArray  a string array of commands to be parsed for more information
   */
  public void unMarkTask(String[] commandArray) {}

  /**
   * Execute the command to delete a task.
   *
   * @param commandArray  a string array of commands to be parsed for more information
   */
  public void delete(String[] commandArray) {}

  /**
   * utility method used to parse the and execute the user command.
   *
   * @param userInput  the raw input string the user entered into the chatbot
   */
  public void parseAndExecuteCommand(String userInput) {
    String[] commandArray = userInput.split(" ");
    String command = commandArray[0];
    try {
      switch (Command.valueOf(command)) {
        case bye:
          return;
        case list:
          break;
        case mark:
          break;
        case unmark:
          break;
        case delete:
          break;
        default:
      }
    } catch (NumberFormatException e) {
      System.out.println("Error: Cannot cast a non-integer into an integer");
    }
  }
}
