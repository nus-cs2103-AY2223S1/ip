import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

  private static final String FILE_DIRECTORY = "data";
  private static final String FILE_NAME = "task.txt";
  private static final String FILE_PATH = FILE_DIRECTORY + "/" + FILE_NAME;

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

  static void addTodoTask(String[] input, boolean status) {
    try {
      if (input.length < 2) {
        throw new DukeException("The description cannot be empty!");
      }
      list.add(new Todo(input[1], status));

      System.out.println("Todo task has been added!");
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    }
  }

  static void addDeadlineTask(String[] input, boolean status) {
    try {
      if (input.length < 2) {
        throw new DukeException("The description cannot be empty!");
      }
      input = input[1].split("\\s*/by\\s*", 2);
      if (input.length < 2) {
        throw new DukeException("The date cannot be empty!");
      }
      list.add(new Deadline(input[0], status, input[1]));

      System.out.println("Deadline task has been added!");
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    }
  }

  static void addEventTask(String[] input, boolean status) {
    try {
      if (input.length < 2) {
        throw new DukeException("The description cannot be empty!");
      }
      input = input[1].split("\\s*/at\\s*", 2);
      if (input.length < 2) {
        throw new DukeException("The date cannot be empty!");
      }
      list.add(new Event(input[0], status, input[1]));

      System.out.println("Event task has been added!");
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    }
  }

  static void loadAllTasks() {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
      String line = br.readLine();

      while (line != null) {
        String[] input = line.split("\\s*\\|\\s*");
        if (input.length < 3) {
          throw (new DukeException("Invalid task format!"));
        }
        String[] data = { input[1], input[2] };

        switch (input[0]) {
          case Todo.SYMBOL:
            addTodoTask(data, data[0] == "1");
            break;
          case Deadline.SYMBOL:
            addDeadlineTask(data, data[0] == "1");
            break;
          case Event.SYMBOL:
            addEventTask(data, data[0] == "1");
            break;
          default:
            System.out.println("Invalid task type!");
            break;
        }

        line = br.readLine();
      }
    } catch (FileNotFoundException e) {
      System.out.println("No task previously saved!");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  static void saveAllTasks() {
    File directory = new File(FILE_DIRECTORY);
    if (!directory.exists()) {
      directory.mkdir();
    }

    try (PrintWriter pw = new PrintWriter(FILE_PATH, "UTF-8")) {
      list.forEach(task -> pw.println(task.saveString()));
    } catch (FileNotFoundException e) {
      System.out.println("Cannot save tasks!");
    } catch (Exception e) {
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

    loadAllTasks();

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
          addTodoTask(input, false);
          break;
        case "deadline":
          addDeadlineTask(input, false);
          break;
        case "event":
          addEventTask(input, false);
          break;
        default:
          System.out.println("Sorry, I don't know what do you mean by that.");
      }

      saveAllTasks();
    }
  }
}
