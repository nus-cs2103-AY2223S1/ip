import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

  private static List<Task> list = new ArrayList<Task>();
  private static Scanner scanner = new Scanner(System.in);

  static void setTaskStatus(String[] input, boolean status) {
    try {
      if (input.length < 2) {
        throw new DukeException("Index cannot be empty!");
      }
      int index = Integer.parseInt(input[1]) - 1;
      if (index < 0 || index >= list.size()) {
        throw (new DukeException("Task " + (index + 1) + " does not exist"));
      }
      list.get(index).setStatus(status);

      System.out.println(
        "Task " +
        (index + 1) +
        " is marked as " +
        (status ? "done" : "not done") +
        "!"
      );
    } catch (NumberFormatException e) {
      System.out.println("Invalid task index!");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  static void deleteTask(String[] input) {
    try {
      if (input.length < 2) {
        throw new DukeException("Index cannot be empty!");
      }
      int index = Integer.parseInt(input[1]) - 1;
      if (index < 0 || index >= list.size()) {
        throw (new DukeException("Task " + (index + 1) + " does not exist"));
      }
      list.remove(index);

      System.out.println("Task " + (index + 1) + " has been removed!");
    } catch (NumberFormatException e) {
      System.out.println("Invalid task index!");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  static void addTodoTask(String[] input) {
    try {
      if (input.length < 2) {
        throw new DukeException("The description cannot be empty!");
      }
      list.add(new Todo(input[1], false));

      System.out.println("Todo task has been added!");
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    }
  }

  static void addDeadlineTask(String[] input) {
    try {
      if (input.length < 2) {
        throw new DukeException("The description cannot be empty!");
      }
      input = input[1].split("\\s*/by\\s*", 2);
      if (input.length < 2) {
        throw new DukeException("The date cannot be empty!");
      }
      list.add(new Deadline(input[0], false, input[1]));

      System.out.println("Deadline task has been added!");
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    }
  }

  static void addEventTask(String[] input) {
    try {
      if (input.length < 2) {
        throw new DukeException("The description cannot be empty!");
      }
      input = input[1].split("\\s*/at\\s*", 2);
      if (input.length < 2) {
        throw new DukeException("The date cannot be empty!");
      }
      list.add(new Event(input[0], false, input[1]));

      System.out.println("Event task has been added!");
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    String logo =
      "______       _     \n" +
      "| ___ \\     | |    \n" +
      "| |_/ / ___ | |__  \n" +
      "| ___ \\/ _ \\| '_ \\ \n" +
      "| |_/ / (_) | |_) |\n" +
      "\\____/ \\___/|_.__/ \n";
    System.out.println("Hello from\n" + logo);
    System.out.println("What can I do for you?");

    while (true) {
      System.out.print("> ");
      String[] input = scanner.nextLine().split(" ", 2);

      switch (input[0]) {
        case "bye":
          System.out.println("Goodbye!");
          return;
        case "list":
          for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ") " + list.get(i).toString());
          }
          break;
        case "mark":
          setTaskStatus(input, true);
          break;
        case "unmark":
          setTaskStatus(input, false);
          break;
        case "delete":
          deleteTask(input);
          break;
        case "todo":
          addTodoTask(input);
          break;
        case "deadline":
          addDeadlineTask((input));
          break;
        case "event":
          addEventTask(input);
          break;
        default:
          System.out.println("Sorry, I don't know what do you mean by that.");
      }
    }
  }
}
