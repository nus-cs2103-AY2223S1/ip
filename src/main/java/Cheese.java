import java.util.Scanner;
import java.util.ArrayList;

public class Cheese {
  public static final String BORDER = "-----";
  public static ArrayList<String> list = new ArrayList<String>();

  public static void main(String[] args) {
    String greeting = new String("Woof! I'm Cheese, your puppy assistant.\n"
            + "What can I do for you?");
    System.out.println(greeting);

    Cheese.chat();
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
        Cheese.printList();
        continue;
      }
      Cheese.list.add(userInput);
      System.out.println("added: " + userInput);
    }
  }

  public static void printList() {
    for (int i = 1; i <= list.size(); i++) {
      System.out.println(i + ". " + list.get(i - 1));
    }
  }
}
