import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
  public static final String BORDER = "-----";
  public static ArrayList<Task> list = new ArrayList<Task>();

  public static void main(String[] args) {
    String greeting = new String("Woof! I'm Cheese, your puppy assistant.\n"
            + "What can I do for you?");
    System.out.println(greeting);

    Duke.chat();
  }

  public static void chat() {
    Scanner scanner = new Scanner(System.in);
    String userInput;

    while(true) {
      System.out.println(BORDER);
      userInput = scanner.nextLine();
      System.out.println(BORDER);

      if (userInput.equals("bye")) {
        System.out.println("So soon? :') Bye bye");
        scanner.close();
        break;
      }

      if (userInput.equals("list")) {
        Duke.printList();
      } else if (userInput.indexOf("mark") == 0) {
        int itemNum = Integer.parseInt(userInput.substring(5));
        if (notInListRange(itemNum)) {
          System.out.println("Invalid list item number.");
          continue;
        }
        Duke.list.get(itemNum - 1).markAsDone();
        System.out.println("Nice! Another task done!");
        System.out.println(Duke.list.get(itemNum - 1));
      } else if (userInput.indexOf("unmark") == 0) {
        int itemNum = Integer.parseInt(userInput.substring(7));
        if (notInListRange(itemNum)) {
          System.out.println("Invalid list item number.");
          continue;
        }
        Duke.list.get(itemNum - 1).markAsNotDone();
        System.out.println("Okie! I've marked this task as not done yet.");
        System.out.println(Duke.list.get(itemNum - 1));
      } else if (userInput.indexOf("todo") == 0){
        String description = userInput.substring(5);
        Task task = new Todo(description);
        Duke.addTask(task);
      } else if (userInput.indexOf("deadline") == 0 && userInput.indexOf("/by") != -1) {
        int byFlagIndex = userInput.indexOf("/by");
        String description = userInput.substring(9, byFlagIndex - 1);
        String deadline = userInput.substring(byFlagIndex + 4);
        Task task = new Deadline(description, deadline);
        Duke.addTask(task);
      } else if (userInput.indexOf("event") == 0 && userInput.indexOf("/at") != -1) {
        int atFlagIndex = userInput.indexOf("/at");
        String description = userInput.substring(6, atFlagIndex - 1);
        String timeInterval = userInput.substring(atFlagIndex + 4);
        Task task = new Event(description, timeInterval);
        Duke.addTask(task);
      } else {
        System.out.println("Sowwy, I don't understand");
      }
    }
  }

  private static void addTask(Task task) {
    Duke.list.add(task);
    System.out.println("Gotcha! I have a paw-fect memory!");
    System.out.println("  " + task);
    System.out.println("You have " + Duke.list.size() + " task(s) in the list.");
  }

  private static boolean notInListRange(int itemNum) {
    return itemNum < 1 || itemNum > Duke.list.size();
  }

  public static void printList() {
    for (int i = 1; i <= Duke.list.size(); i++) {
      System.out.println(i + ". " + Duke.list.get(i - 1));
    }
  }
}
