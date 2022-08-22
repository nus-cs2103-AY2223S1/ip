import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.IntStream;

class Duke {
  private static ArrayList<Task> taskList;

  public static void greet() {
    String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
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
    System.out.println("_______________________________________________________" + "\n\t" + str + "\n"
        + "_______________________________________________________");
  }

  public static String wrapMessage(String str, String taskDescription) {
    return String
        .format(str + "\n\t\t" + taskDescription + "\n\tNow you have " + taskList.size() + " tasks in the list.");
  }

  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Storage storage = new Storage();
    try {
      taskList = storage.readFile();
    } catch (DukeException e) {
      e.printStackTrace();
    }
    String input = "";

    Duke.greet();

    while (!input.equals("bye")) {
      try {
        input = reader.readLine();
        if (input == null || input.equals("bye")) {
          reader.close();
          break;
        }

        String[] inputArray = input.split(" ", 2);

        switch (inputArray[0]) {
        case "list": {
          String[] strArray = IntStream.range(0, taskList.size())
              .mapToObj(i -> String.format("%d.%s", i + 1, taskList.get(i).toString())).toArray(String[]::new);
          Duke.printMessage(strArray);
          break;
        }
        case "mark": {
          try {
            Task task = taskList.get(Integer.parseInt(inputArray[1]) - 1);
            task.markAsDone();
            Duke.printMessage(String.format("Nice! I've marked this task as done:\n\t\t %s", task.toString()));
          } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Duke.printMessage("Please use the format: mark <integer>");
          } catch (IndexOutOfBoundsException e) {
            Duke.printMessage("Invalid task index");
          }
          break;
        }
        case "unmark": {
          try {
            Task task = taskList.get(Integer.parseInt(inputArray[1]) - 1);
            task.markNotDone();
            Duke.printMessage(String.format("OK, I've marked this task as not done yet:\n\t\t %s", task.toString()));
          } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Duke.printMessage("Please use the format: unmark <integer>");
          } catch (IndexOutOfBoundsException e) {
            Duke.printMessage("Invalid task index");
          }
          break;
        }
        case "todo": {
          try {
            String withoutPrefix = inputArray[1];
            Todo todo = new Todo(withoutPrefix);
            taskList.add(todo);
            Duke.printMessage(wrapMessage("Got it. I've added this task:", todo.toString()));
          } catch (ArrayIndexOutOfBoundsException e) {
            Duke.printMessage("The description of a todo cannot be empty.");
          }
          break;
        }
        case "deadline": {
          try {
            String withoutPrefix = inputArray[1];
            String[] strArray = withoutPrefix.split("/");
            Deadline deadline = new Deadline(strArray[0].strip(), strArray[1].split(" ", 2)[1]);
            taskList.add(deadline);
            Duke.printMessage(wrapMessage("Got it. I've added this task:", deadline.toString()));
          } catch (ArrayIndexOutOfBoundsException e) {
            if (inputArray.length == 1) {
              Duke.printMessage("The description of a deadline cannot be empty.");
            } else {
              Duke.printMessage("Please specify a day, date, or time");
            }
          }
          break;
        }
        case "event": {
          try {
            String withoutPrefix = inputArray[1];
            String[] strArray = withoutPrefix.split("/");
            Event event = new Event(strArray[0].strip(), strArray[1].split(" ", 2)[1]);
            taskList.add(event);
            Duke.printMessage(wrapMessage("Got it. I've added this task:", event.toString()));
          } catch (ArrayIndexOutOfBoundsException e) {
            if (inputArray.length == 1) {
              Duke.printMessage("The description of a event cannot be empty.");
            } else {
              Duke.printMessage("Please specify a day, date, or time");
            }
          }
          break;
        }
        case "delete": {
          try {
            String withoutPrefix = inputArray[1];
            int index = Integer.parseInt(withoutPrefix);
            Task task = taskList.get(index - 1);
            taskList.remove(index - 1);
            Duke.printMessage(wrapMessage("Noted. I've removed this task:", task.toString()));
          } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Duke.printMessage("Please use the format: delete <integer>");
          } catch (IndexOutOfBoundsException e) {
            Duke.printMessage("Invalid task index");
          }
          break;
        }
        default:
          Duke.printMessage("I'm sorry, but I don't know what that means :-(");
        }
      } catch (IOException e) {
      }
    }
    try {
      storage.saveFile(taskList);
    } catch (DukeException e) {
      e.printStackTrace();
    }
    Duke.exitMessage();
  }
}
