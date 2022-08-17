import java.util.Scanner;

public class Cheese {
  public static void main(String[] args) {
    final String BORDER = "-----";

    String greeting = new String("Woof! I'm Cheese, your puppy assistant.\n"
            + "What can I do for you?\n"
            + BORDER);
    System.out.println(greeting);

    Scanner scanner = new Scanner(System.in);
    String userInput;

    while(true) {
      userInput = scanner.nextLine();
      System.out.println(BORDER);
      if (userInput.equals("bye")) {
        System.out.println("So soon? :') Bye bye");
        break;
      }
      System.out.println(userInput + "\n" + BORDER);
    }
  }
}
