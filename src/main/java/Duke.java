import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  private ArrayList<Task> listOfItems = new ArrayList<Task>();

  private void greet() {
    String logo =
      " ____        _        \n" +
      "|  _ \\ _   _| | _____ \n" +
      "| | | | | | | |/ / _ \\\n" +
      "| |_| | |_| |   <  __/\n" +
      "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
  }

  private void run(Scanner scanner) throws Exception {
    // Run duke as long as command is not bye
    while (true) {
      String txt = scanner.nextLine();
      String[] commands = txt.split(" ", 2);
      if (commands[0].equals("bye")) {
        this.bye();
        break;
      } else {
        switch (commands[0]) {
          case ("list"):
            this.list();
            break;
          case ("add"):
            this.add(commands[1]);
            break;
          case ("mark"):
            this.mark(Integer.parseInt(commands[1]));
            break;
          case ("unmark"):
            this.unmark(Integer.parseInt(commands[1]));
            break;
          default:
            throw new DukeException("Uknown command received");
        }
      }

      System.out.println("\n");
    }
  }

  private void bye() {
    System.out.println("Bye. Hope to see you again soon!");
  }

  // Add tasks
  private void add(String txt) {
    this.listOfItems.add(new Task(txt));
    System.out.println("added: " + txt);
  }

  // List functions
  private void list() {
    if (listOfItems.size() == 0) {
      System.out.println("Use add keyword to add tasks!");
    } else {
      System.out.println("Here are the tasks in your list:");
      for (int i = 0; i < listOfItems.size(); i++) {
        System.out.println((i + 1) + ". " + listOfItems.get(i).toString());
      }
    }
  }

  private void mark(int index) throws DukeException {
    if (index <= 0 || index > listOfItems.size()) {
      throw new DukeException("No such tasks found");
    } else {
      Task task = listOfItems.get(index - 1);
      task.markAsDone();
      System.out.println("Fuyoh! I've marked this task as done:");
      System.out.println(task.toString());
    }
  }

  private void unmark(int index) throws DukeException {
    if (index <= 0 || index > listOfItems.size()) {
      throw new DukeException("No such tasks found");
    } else {
      Task task = listOfItems.get(index - 1);
      task.markUndone();
      System.out.println("Aiyah! I've marked this task as not done yet: ");
      System.out.println(task.toString());
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // Create a Scanner object

    Duke duke = new Duke();
    duke.greet();
    try {
      duke.run(scanner);
    } catch (DukeException e1) {
      System.out.println(e1);
    } catch (Exception e2) {
      System.out.println(e2);
    }
    scanner.close();
  }
}
