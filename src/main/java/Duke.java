import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
  /* String ArrayList to store text entered by the user.
   * Won't go past 100 inputs.
   */
  private static ArrayList<String> userInputs = new ArrayList<String>();

  private static void actionAfterInput(String userInput) {
    userInputs.add(userInput);
    System.out.println("added: " + userInput + "\n");
  }

  private static void goodbye() {
    System.out.println("MumBot: Goodbyeeee sweetheart <3");
  }

  private static void list() {
    for (int i = 0; i < userInputs.size(); i++) {
      System.out.println((i + 1) + ". " + userInputs.get(i));
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    // Welcome message
    System.out.println("MumBot: Hi dear! You are precious <3\n");

    /*
     * Take in user input. As long as input is not bye, calls the function
     * to act on the input. If input is bye, then calls the goodbye function.
     * All inputs are considered as Strings.
     */
    System.out.print("You: ");
    Scanner sc = new Scanner(System.in);

    String input;
    while (!(input = sc.nextLine()).equals("Bye")) {
      if (input.equals("list")) {
        list();
      } else {
        actionAfterInput(input);
      }
      System.out.print("You: ");
    }

    goodbye();
    System.exit(0);
  }
}
