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
      userInput = scanner.nextLine();
      System.out.println(BORDER);

      String[] inputArray = userInput.split(" ", 2);
      String command = inputArray[0];

      switch (command) {
        case "bye":
          System.out.println("Going so soon? :') Bye");
          scanner.close();
          return;
        case "list":
          Cheese.printList();
          break;
        case "mark":
          Cheese.markTaskAsDone(Integer.parseInt(inputArray[1]));
          break;
        case "unmark":
          Cheese.markTaskAsUndone(Integer.parseInt(inputArray[1]));
          break;
        case "todo":
          Task todo = new Todo(inputArray[1]);
          Cheese.addTask(todo);
          break;
        case "deadline":
          String[] deadlineInputArray = inputArray[1].split(" /by ", 2);
          Task deadline = new Deadline(deadlineInputArray[0], deadlineInputArray[1]);
          Cheese.addTask(deadline);
          break;
        case "event":
          String[] eventInputArray = inputArray[1].split(" /at ", 2);
          Task event = new Event(eventInputArray[0], eventInputArray[1]);
          Cheese.addTask(event);
          break;
        default:
          System.out.println("Sowwy, I don't understand");
      }
    }
  }

  private static void markTaskAsDone(int itemNum) {
    Task selectedTask = Cheese.list.get(itemNum - 1);
    selectedTask.markAsDone();
    System.out.println("Paw-some! Another task done!");
    System.out.println(selectedTask);
  }

  private static void markTaskAsUndone(int itemNum) {
    Task selectedTask = Cheese.list.get(itemNum - 1);
    selectedTask.markAsNotDone();
    System.out.println("Okay, I've marked this task as not done yet.");
    System.out.println(selectedTask);
  }

  private static void addTask(Task task) {
    Cheese.list.add(task);
    System.out.println("Gotcha! I have a paw-fect memory!");
    System.out.println("  " + task);
    System.out.println("You have " + Cheese.list.size() + " task(s) in the list.");
  }

  private static boolean notInListRange(int itemNum) {
    return itemNum < 1 || itemNum > Cheese.list.size();
  }

  private static void printList() {
    for (int i = 1; i <= Cheese.list.size(); i++) {
      System.out.println(i + ". " + Cheese.list.get(i - 1));
    }
  }
}
