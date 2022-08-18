import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    for (String str : strArray) {
      System.out.println("\t" + str);
    }
    System.out.println("_______________________________________________________");
  }

  public static void printMessage(String str) {
    System.out.println("_______________________________________________________" +
        "\n\t " + str + "\n" +
        "_______________________________________________________");
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

        String[] inputArray = input.split(" ");

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
          default:
            taskList.add(new Task(input));
            Duke.printMessage(" added: " + input);
        }
      } catch (IOException e) {
      }
    }
    Duke.exitMessage();
  }
}
