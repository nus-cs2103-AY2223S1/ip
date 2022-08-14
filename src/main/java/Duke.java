import java.util.Scanner;

public class Duke {
  public static void inputAction(String input) {
    System.out.println(input);
  }

  public static void goodbye() {
    System.out.println("Goodbye.");
  }

  public static void main(String[] args) {
    // Welcome message
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);

    /*
     * Take in user input. As long as input is not bye, calls the function
     * to act on the input. If input is bye, then calls the goodbye function.
     * All inputs are considered as Strings.
     */
    Scanner sc = new Scanner(System.in);

    String input;
    while (!(input = sc.nextLine()).equals("Bye")) {
      inputAction(input);
    }

    goodbye();
    System.exit(0);
  }
}
