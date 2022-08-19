import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

  private static Scanner scanner = new Scanner(System.in);

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

    List<Task> list = new ArrayList<Task>();

    while (true) {
      System.out.print("> ");
      String[] input = scanner.nextLine().split(" ", 2);
      String command = input[0];

      if (command.equals("bye")) {
        System.out.println("Goodbye!");
        break;
      } else if (command.equals("mark")) {
        try {
          int index = Integer.parseInt(input[1]) - 1;

          list.set(index, list.get(index).setStatus(true));

          System.out.println("Task marked as done!");
          System.out.println(list.get(index).toString());
        } catch (Exception e) {
          System.out.println("Invalid task index!");
        }
      } else if (command.equals("unmark")) {
        try {
          int index = Integer.parseInt(input[1]) - 1;
          list.set(index, list.get(index).setStatus(false));

          System.out.println("Task marked as not done!");
          System.out.println(list.get(index).toString());
        } catch (Exception e) {
          System.out.println("Invalid task index!");
        }
      } else if (command.equals("delete")) {
        try {
          int index = Integer.parseInt(input[1]) - 1;
          Task task = list.get(index);
          list.remove(index);

          System.out.println("Task has been deleted!: " + task.toString());
          System.out.println(
            "Now you have " + list.size() + " tasks in the list"
          );
        } catch (Exception e) {
          System.out.println("Invalid task index!");
        }
      } else if (command.equals("list")) {
        for (int i = 0; i < list.size(); i++) {
          System.out.println((i + 1) + ") " + list.get(i).toString());
        }
      } else if (command.equals("todo")) {
        if (input.length < 2) {
          System.out.println("The description cannot be empty!");
          continue;
        }

        Task task = new Todo(input[1], false);
        list.add(task);

        System.out.println("Task added to list!: " + task.toString());
        System.out.println(
          "Now you have " + list.size() + " tasks in the list"
        );
      } else if (command.equals("deadline")) {
        input = input[1].split(" /by ", 2);
        if (input.length < 2) {
          System.out.println(
            "The description and deadline date cannot be empty!"
          );
          continue;
        }

        Task task = new Deadline(input[0], false, input[1]);
        list.add(task);

        System.out.println("Task added to list!: " + task.toString());
        System.out.println(
          "Now you have " + list.size() + " tasks in the list"
        );
      } else if (command.equals("event")) {
        input = input[1].split(" /at ", 2);
        if (input.length < 2) {
          System.out.println("The description and event date cannot be empty!");
          continue;
        }

        Task task = new Event(input[0], false, input[1]);
        list.add(task);

        System.out.println("Task added to list!: " + task.toString());
        System.out.println(
          "Now you have " + list.size() + " tasks in the list"
        );
      } else {
        System.out.println("Sorry, I don't know what do you mean by that.");
      }
    }
  }
}
