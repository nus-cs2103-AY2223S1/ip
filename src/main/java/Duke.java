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
        break;
      }
      if (userInput.equals("list")) {
        Duke.printList();
      } else if (userInput.matches("mark \\d+$")) {
        int itemNum = Integer.parseInt(userInput.substring(5));
        if (notInListRange(itemNum)) {
          System.out.println("Invalid list item number.");
          continue;
        }
        Duke.list.get(itemNum - 1).markAsDone();
        System.out.println("Nice! Another task done!");
        System.out.println(Duke.list.get(itemNum - 1));
      } else if (userInput.matches("unmark \\d+$")) {
        int itemNum = Integer.parseInt(userInput.substring(7));
        if (notInListRange(itemNum)) {
          System.out.println("Invalid list item number.");
          continue;
        }
        Duke.list.get(itemNum - 1).markAsNotDone();
        System.out.println("Okie! I've marked this task as not done yet.");
        System.out.println(Duke.list.get(itemNum - 1));
      } else {
        Duke.list.add(new Todo(userInput));
        System.out.println("added: " + userInput);
      }
    }
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
