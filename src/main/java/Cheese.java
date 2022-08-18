import java.util.Scanner;
import java.util.ArrayList;

public class Cheese {
  public static final String BORDER = "-----";
  public static ArrayList<Task> list = new ArrayList<Task>();

  public static void main(String[] args) {
    Cheese.greet();
    Cheese.chat();
  }

  private static void greet() {
    String greeting = new String("Woof! I'm Cheese, your puppy assistant.\n"
        + "What can I do for you?");
    System.out.println(greeting);
  }

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
        switch (command) {
          case "bye":
            Cheese.validateOneWordCommand(inputArray);
            System.out.println("Going so soon? :') Bye");
            scanner.close();
            return;
          case "list":
            Cheese.validateOneWordCommand(inputArray);
            Cheese.printList();
            break;
          case "mark":
            Task taskToMark = Cheese.selectTask(inputArray);
            taskToMark.markAsDone();
            break;
          case "unmark":
            Task taskToUnmark = Cheese.selectTask(inputArray);
            taskToUnmark.markAsNotDone();
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

  private static void validateOneWordCommand(String[] inputArray) throws CheeseException {
    if (inputArray.length != 1) {
      throw new CheeseException();
    }
  }

  private static Task selectTask(String[] inputArray) throws CheeseException, NumberFormatException {
    if (inputArray.length == 1 || inputArray[1].length() == 0) {
      throw new CheeseException("Sowwy, the item number cannot be empty.");
    }
    int itemIdx = Integer.parseInt(inputArray[1]) - 1;
    if (itemIdx < 0 || itemIdx >= Cheese.list.size()) {
      throw new CheeseException("Item number is not in list range.");
    }
    return Cheese.list.get(itemIdx);
  }

  private static void addTask(Task task) {
    Cheese.list.add(task);
    System.out.println("Gotcha! I have a paw-fect memory!");
    System.out.println("  " + task);
    System.out.println("You have " + Cheese.list.size() + " task(s) in the list.");
  }

  private static void printList() {
    for (int i = 1; i <= Cheese.list.size(); i++) {
      System.out.println(i + ". " + Cheese.list.get(i - 1));
    }
  }
}
