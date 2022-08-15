import java.util.Scanner;

public class Duke {

  public static void main(String[] args) {
    String logo =
      " ____        _        \n" +
      "|  _ \\ _   _| | _____ \n" +
      "| | | | | | | |/ / _ \\\n" +
      "| |_| | |_| |   <  __/\n" +
      "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    Scanner scanner = new Scanner(System.in); // Create a Scanner object
    while (true) {
      String txt = scanner.nextLine();
      if (txt.toLowerCase().equals("bye")) {
        break;
      }
      System.out.println(txt);
    }

    System.out.println("Bye. Hope to see you again soon!");

    scanner.close();
  }
}
