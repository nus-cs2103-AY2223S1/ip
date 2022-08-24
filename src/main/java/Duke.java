import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.IntStream;

class Duke {
  private static ArrayList<Task> taskList;

  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Storage storage = new Storage();
    try {
      taskList = storage.readFile();
    } catch (DukeException e) {
      e.printStackTrace();
    }
    String input = "";

    Ui.greet();

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
          Ui.printMessage(strArray);
          break;
        }
        case "mark": {
          try {
            Task task = taskList.get(Integer.parseInt(inputArray[1]) - 1);
            task.markAsDone();
            Ui.printMessage(String.format("Nice! I've marked this task as done:\n\t\t %s", task.toString()));
          } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printMessage("Please use the format: mark <integer>");
          } catch (IndexOutOfBoundsException e) {
            Ui.printMessage("Invalid task index");
          }
          break;
        }
        case "unmark": {
          try {
            Task task = taskList.get(Integer.parseInt(inputArray[1]) - 1);
            task.markNotDone();
            Ui.printMessage(String.format("OK, I've marked this task as not done yet:\n\t\t %s", task.toString()));
          } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printMessage("Please use the format: unmark <integer>");
          } catch (IndexOutOfBoundsException e) {
            Ui.printMessage("Invalid task index");
          }
          break;
        }
        case "todo": {
          try {
            String withoutPrefix = inputArray[1];
            Todo todo = new Todo(withoutPrefix);
            taskList.add(todo);
            Ui.printMessage(Ui.wrapMessage("Got it. I've added this task:", todo.toString(), taskList));
          } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printMessage("The description of a todo cannot be empty.");
          }
          break;
        }
        case "deadline": {
          try {
            String withoutPrefix = inputArray[1];
            String[] strArray = withoutPrefix.split("/");
            Deadline deadline = new Deadline(strArray[0].strip(), strArray[1].split(" ", 2)[1]);
            taskList.add(deadline);
            Ui.printMessage(Ui.wrapMessage("Got it. I've added this task:", deadline.toString(), taskList));
          } catch (ArrayIndexOutOfBoundsException e) {
            if (inputArray.length == 1) {
              Ui.printMessage("The description of a deadline cannot be empty.");
            } else {
              Ui.printMessage("Please specify a day, date, or time");
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
            Ui.printMessage(Ui.wrapMessage("Got it. I've added this task:", event.toString(), taskList));
          } catch (ArrayIndexOutOfBoundsException e) {
            if (inputArray.length == 1) {
              Ui.printMessage("The description of a event cannot be empty.");
            } else {
              Ui.printMessage("Please specify a day, date, or time");
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
            Ui.printMessage(Ui.wrapMessage("Noted. I've removed this task:", task.toString(), taskList));
          } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printMessage("Please use the format: delete <integer>");
          } catch (IndexOutOfBoundsException e) {
            Ui.printMessage("Invalid task index");
          }
          break;
        }
        default:
          Ui.printMessage("I'm sorry, but I don't know what that means :-(");
        }
      } catch (IOException e) {
      }
    }
    try {
      storage.saveFile(taskList);
    } catch (DukeException e) {
      e.printStackTrace();
    }
    Ui.exitMessage();
  }
}
