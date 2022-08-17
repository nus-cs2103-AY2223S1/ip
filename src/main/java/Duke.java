import java.util.Scanner;

public class Duke {
  public static void inputAction(String input) {
    System.out.println("MumBot: " + input + "\n\n");
  }

  public static void goodbye() {
    System.out.println("MumBot: Goodbyeeee sweetheart <3");
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
      inputAction(input);
      System.out.print("You: ");
    }

    goodbye();
    System.exit(0);
  }
}
