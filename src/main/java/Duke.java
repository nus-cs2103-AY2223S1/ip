import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  private static String LOGO =
      " ____        _        \n"
          + "|  _ \\ _   _| | _____ \n"
          + "| | | | | | | |/ / _ \\\n"
          + "| |_| | |_| |   <  __/\n"
          + "|____/ \\__,_|_|\\_\\___|\n";
  private static ArrayList<Task> allTasks = new ArrayList<>();

  private static void greet() {
    System.out.println("Hello from\n" + Duke.LOGO);
    System.out.println("What can I do for you?");
  }

  private static void listTasks() {
    String output = "";
    for (int i = 0; i < Duke.allTasks.size(); i++) {
      output += "\n" + (i + 1) + ". " + Duke.allTasks.get(i).toString();
    }
    System.out.println(output);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Duke.greet();
    while (true) {
      System.out.println("------------------------------");
      System.out.print(": ");
      String userInput = scanner.nextLine();
      String[] inputArray = userInput.split(" ");
      String cmd = inputArray[0];
      int taskIndex;
      try {
        switch (Commands.valueOf(cmd)) {
          case bye:
            System.out.println(" Bye. Hope to see you again soon!");
            return;
          case list:
            Duke.listTasks();
            break;
          case mark:
            taskIndex = Integer.parseInt(inputArray[1]) - 1;
            Duke.allTasks.get(taskIndex).markAsDone();
            break;
          case unmark:
            taskIndex = Integer.parseInt(inputArray[1]) - 1;
            Duke.allTasks.get(taskIndex).unmark();
            break;
          case delete:
            taskIndex = Integer.parseInt(inputArray[1]) - 1;
            Task task = Duke.allTasks.get(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            allTasks.remove(taskIndex);
            break;
          default:
            Task newTask = Task.createTask(userInput);
            Duke.allTasks.add(newTask);
        }
      } catch (NumberFormatException e) {
        System.out.println("Error: Cannot cast a non-integer into an integer");
      } catch (DukeException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

      System.out.println("------------------------------\n");
    }
  }
}
