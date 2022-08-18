import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.stream.IntStream;

class Duke {
  private static ArrayList<Task> taskList;

  public static void greet() {
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
  }

  public static void exitMessage() {
    Duke.printMessage("Bye. Hope to see you again soon!");
  }

  public static void printMessage(String[] strArray) {
    System.out.println("_______________________________________________________");
    System.out.println("\tHere are the tasks in your list:");
    for (String str : strArray) {
      System.out.println("\t" + str);
    }
    System.out.println("_______________________________________________________");
  }

  public static void printMessage(String str) {
    System.out.println("_______________________________________________________" +
        "\n\t" + str + "\n" +
        "_______________________________________________________");
  }

  public static String wrapMessage(String str) {
    return String.format("Got it. I've added this task:\n\t\t" + str + "\n\tNow you have " + taskList.size()
        + " tasks in the list.");
  }

  public static void main(String[] args) {
    taskList = new ArrayList<>();
    BufferedReader reader;
    String input = "";

    Duke.greet();

    while (!input.equals("bye")) {
      try {
        reader = new BufferedReader(new InputStreamReader(System.in));
        input = reader.readLine();
        if (input.equals("bye")) {
          reader.close();
          break;
        }

        String[] inputArray = input.split(" ", 2);

        switch (inputArray[0]) {
          case "list": {
            String[] strArray = IntStream.range(0, taskList.size())
                .mapToObj(i -> String.format("%d.%s", i + 1, taskList.get(i).toString()))
                .toArray(String[]::new);
            Duke.printMessage(strArray);
          }
            break;
          case "mark": {
            Task task = taskList.get(Integer.parseInt(inputArray[1]) - 1);
            task.markAsDone();
            Duke.printMessage(String.format("Nice! I've marked this task as done:\n\t\t %s", task.toString()));
          }
            break;
          case "unmark": {
            Task task = taskList.get(Integer.parseInt(inputArray[1]) - 1);
            task.markNotDone();
            Duke.printMessage(String.format("OK, I've marked this task as not done yet:\n\t\t %s", task.toString()));
          }
            break;
          case "todo": {
            String withoutPrefix = inputArray[1];
            taskList.add(new Todo(withoutPrefix));
            Duke.printMessage(wrapMessage(input));
          }
            break;
          case "deadline": {
            String withoutPrefix = inputArray[1];
            String[] strArray = withoutPrefix.split("/");
            Deadline deadline = new Deadline(strArray[0].strip(), strArray[1].split(" ", 2)[1]);
            taskList.add(deadline);
            Duke.printMessage(wrapMessage(deadline.toString()));
          }
            break;
          case "event": {
            String withoutPrefix = inputArray[1];
            String[] strArray = withoutPrefix.split("/");
            Event event = new Event(strArray[0].strip(), strArray[1].split(" ", 2)[1]);
            taskList.add(event);
            Duke.printMessage(wrapMessage(event.toString()));
          }
            break;
          default:
            taskList.add(new Task(input));
            Duke.printMessage("added: " + input);
        }
      } catch (IOException e) {
      }
    }
    Duke.exitMessage();
  }
}
