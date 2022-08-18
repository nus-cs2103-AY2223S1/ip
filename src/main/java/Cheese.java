import java.util.Scanner;
import java.util.ArrayList;

public class Cheese {
  /** Constant to represent border during conversation */
  public static final String BORDER = "-----";

  /** Array list to store list of tasks */
  public static ArrayList<Task> list = new ArrayList<Task>();

  public static void main(String[] args) {
    Cheese.greet();
    Cheese.chat();
  }

  /** Greets user */
  private static void greet() {
    String greeting = new String("Woof! I'm Cheese, your puppy assistant.\n"
        + "What can I do for you?");
    System.out.println(greeting);
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
            Cheese.printList();
            break;
          case mark:
            Task taskToMark = Cheese.getTaskFromList(inputArray);
            taskToMark.markAsDone();
            break;
          case unmark:
            Task taskToUnmark = Cheese.getTaskFromList(inputArray);
            taskToUnmark.markAsNotDone();
            break;
          case delete:
            Task taskToDelete = Cheese.getTaskFromList(inputArray);
            Cheese.deleteTask(taskToDelete);
            break;
          default:
            Task task = Task.createTask(inputArray);
            Cheese.addTask(task);
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

  /**
   * Deletes given task from list
   * 
   * @param taskToDelete given task to delete
   */
  private static void deleteTask(Task taskToDelete) {
    Cheese.list.remove(taskToDelete);
    System.out.println("Gotcha! I'll forget about this task!");
    System.out.println("  " + taskToDelete);
    System.out.println("You have " + Cheese.list.size() + " task(s) remaining.");
  }

  /**
   * Adds given task to list
   * 
   * @param task given task to add
   */
  private static void addTask(Task task) {
    Cheese.list.add(task);
    System.out.println("Gotcha! I have a paw-fect memory!");
    System.out.println("  " + task);
    System.out.println("You have " + Cheese.list.size() + " task(s) in the list.");
  }

  /** Prints list */
  private static void printList() {
    for (int i = 1; i <= Cheese.list.size(); i++) {
      System.out.println(i + ". " + Cheese.list.get(i - 1));
    }
  }
}
