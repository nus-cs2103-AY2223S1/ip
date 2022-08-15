import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  private ArrayList<String> listOfItems = new ArrayList<String>();

  private void greet() {
    String logo =
      " ____        _        \n" +
      "|  _ \\ _   _| | _____ \n" +
      "| | | | | | | |/ / _ \\\n" +
      "| |_| | |_| |   <  __/\n" +
      "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println("Hello! I'm Duke\nWhat can I do for you?");
  }

  private void echo(String txt) {
    if (txt.equals("list")) {
      for (int i = 0; i < listOfItems.size(); i++) {
        System.out.println(i + ". " + listOfItems.get(i));
      }
    } else {
      this.listOfItems.add(txt);
      System.out.println("added: " + txt);
    }
  }

  public static void main(String[] args) {
    Duke duke = new Duke();
    duke.greet();
    Scanner scanner = new Scanner(System.in); // Create a Scanner object
    // Run duke as long as command is not bye
    while (true) {
      String txt = scanner.nextLine();
      if (txt.toLowerCase().equals("bye")) {
        break;
      }
      duke.echo(txt);
    }

    System.out.println("Bye. Hope to see you again soon!");

    scanner.close();
  }
}
