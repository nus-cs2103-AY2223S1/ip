import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Cheese {
  /** Constant to represent border during conversation */
  public static final String BORDER = "-----";

  /** Array list to store list of tasks */
  private static TaskList taskList = new TaskList();

  public static void main(String[] args) {
    Cheese.greet();
    try {
      Cheese.loadData();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    Cheese.chat();
  }

  /** Greets user */
  private static void greet() {
    String greeting = new String("Woof! I'm Cheese, your puppy assistant.\n"
        + "What can I do for you?");
    System.out.println(greeting);
  }

  private static void loadData() throws IOException {
    try {
      File cheeseFile = new File("data/cheese.txt");
      cheeseFile.createNewFile();

      Scanner cheeseScanner = new Scanner(cheeseFile);
      while (cheeseScanner.hasNext()) {
        taskList.loadTask(cheeseScanner.nextLine());
      }
      cheeseScanner.close();
    } catch (CheeseException e) {
      System.out.println(e.getMessage());
    }
  }

  /** Chats with user */
  private static void chat() {
    Scanner scanner = new Scanner(System.in);
    String userInput;

    while (true) {
      System.out.println(BORDER);
      System.out.print("~ ");
      userInput = scanner.nextLine();
      System.out.println(BORDER);

      String[] inputArray = userInput.split(" ", 2);
      String command = inputArray[0];

      try {
        Cheese.validateCommand(command);
        switch (Command.valueOf(command)) {
          case bye:
            Cheese.validateOneWordCommand(inputArray);
            System.out.println("Going so soon? :') Bye");
            scanner.close();
            return;
          case list:
            Cheese.validateOneWordCommand(inputArray);
            taskList.printTaskList();
            break;
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
  }

  /**
   * Checks if given command is valid
   * 
   * @param command given command from the user
   * @throws CheeseException if given command is invalid
   */
  private static void validateCommand(String command) throws CheeseException {
    for (Command cmd : Command.values()) {
      if (cmd.name().equals(command)) {
        return;
      }
    }
    throw new CheeseException();
  }

  /**
   * Checks if given command has no arguments
   * 
   * @param inputArray array containing user input after splitting by space
   * @throws CheeseException if given command contains extra arguments
   */
  private static void validateOneWordCommand(String[] inputArray) throws CheeseException {
    if (inputArray.length != 1) {
      throw new CheeseException();
    }
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
