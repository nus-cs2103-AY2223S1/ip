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
            if (inputArray.length != 1) {
              throw new CheeseException();
            }
            System.out.println("Going so soon? :') Bye");
            scanner.close();
            return;
          case "list":
            if (inputArray.length != 1) {
              throw new CheeseException();
            }
            Cheese.printList();
            break;
          case "mark":
            Cheese.markTaskAsDone(Integer.parseInt(inputArray[1]));
            break;
          case "unmark":
            Cheese.markTaskAsUndone(Integer.parseInt(inputArray[1]));
            break;
          case "todo":
            if (inputArray.length != 2) {
              throw new CheeseException("The description of a todo cannot be empty.");
            }
            Task todo = new Todo(inputArray[1]);
            Cheese.addTask(todo);
            break;
          case "deadline":
            if (inputArray.length != 2) {
              throw new CheeseException("The description of a deadline cannot be empty.");
            }
            String[] deadlineInputArray = inputArray[1].split(" /by ", 2);
            if (deadlineInputArray.length != 2) {
              throw new CheeseException("The description/deadline cannot be empty.");
            }
            Task deadline = new Deadline(deadlineInputArray[0], deadlineInputArray[1]);
            Cheese.addTask(deadline);
            break;
          case "event":
            if (inputArray.length != 2) {
              throw new CheeseException("The description of an event cannot be empty.");
            }
            String[] eventInputArray = inputArray[1].split(" /at ", 2);
            if (eventInputArray.length != 2) {
              throw new CheeseException("The description/event time cannot be empty.");
            }
            Task event = new Event(eventInputArray[0], eventInputArray[1]);
            Cheese.addTask(event);
            break;
          default:
            throw new CheeseException();
        }
      } catch (NumberFormatException e) {
        System.out.println("Cannot convert non-integer to integer");
      } catch (CheeseException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static void markTaskAsDone(int itemNum) throws CheeseException {
    Task selectedTask = Cheese.selectTask(itemNum);
    selectedTask.markAsDone();
    System.out.println("Paw-some! Another task done!");
    System.out.println(selectedTask);
  }

  private static void markTaskAsUndone(int itemNum) throws CheeseException {
    Task selectedTask = Cheese.selectTask(itemNum);
    selectedTask.markAsNotDone();
    System.out.println("Okay, I've marked this task as not done yet.");
    System.out.println(selectedTask);
  }

  private static Task selectTask(int itemNum) throws CheeseException {
    if (itemNum < 1 || itemNum > Cheese.list.size()) {
      throw new CheeseException("Item number is not in range.");
    }
    return Cheese.list.get(itemNum - 1);
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
