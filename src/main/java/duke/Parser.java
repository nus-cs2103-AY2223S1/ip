package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
  public Parser(){}

  public static String parseDate(String dateAndTime) {
    LocalDate d = LocalDate.parse(dateAndTime);
    String date = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    return date;
  }

  public static void parseCommand(String command, TaskList taskList, Ui ui) {
    try{
      if (command.equals("bye")) {
        ui.quit();
      } else if (command.equals("list")) {
        ui.listOutTasks(taskList);
      } else {
        String[] splitStr = command.split(" ");
        Task taskToMark = getTaskToMark(splitStr,taskList);
        Task taskToAdd = getTaskToAdd(command);
        Task taskToDelete = getTaskToDelete(splitStr,taskList);

        if (taskToMark != null) {
          String action = splitStr[0];
          if (action.equals("mark")) {
            ui.markTask(taskToMark);
          } else {
            ui.unmarkTask(taskToMark);
          }
        } else if (taskToAdd != null) {
          ui.addTask(taskList,taskToAdd);
        } else if (taskToDelete != null) {
          ui.deleteTask(taskList,taskToDelete);
        } else {
          throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
      }
    } catch (DukeException de) {
        System.out.println(de);
    }
  }
  private static Task getTaskToDelete(String[] splitStr, TaskList tasklist) throws DukeException {
    try {
      if (splitStr.length != 2) return null;
      int index = Integer.parseInt(splitStr[1]);
      String action = splitStr[0];
      boolean validAction = action.equals("delete");
      boolean validIndex = index > 0 && index <= tasklist.getSize();
      if (validIndex && validAction) {
        return tasklist.getTask(index-1);
      } else if (validAction && !validIndex) {
        throw new DukeException("☹ OOPS!!! Please enter a valid task number to delete");
      }
      return null;
    } catch (NumberFormatException e) {
      return null;
    }
  }
  private static Task getTaskToMark(String[] splitStr, TaskList taskList) throws DukeException {
    try {
      if (splitStr.length != 2) return null;
      int index = Integer.parseInt(splitStr[1]);
      String action = splitStr[0];
      boolean validAction = action.equals("mark") || action.equals("unmark");
      boolean validIndex = index > 0 && index <= taskList.getSize();
      if (validIndex && validAction) {
        return taskList.getTask(index-1);
      } else if (validAction && !validIndex) {
        throw new DukeException("☹ OOPS!!! Please enter a valid task number to mark/unmark");
      }
      return null;
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private static Task getTaskToAdd(String str) throws DukeException {
    String[] splitStr = str.split(" ");
    String type = splitStr[0];
      if (type.equals("todo")) {
        if (splitStr.length < 2)throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        String description = str.substring(type.length() + 1);
        return new ToDo(description);
      } else if (type.equals("deadline")) {
        if (splitStr.length < 2)throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        if (str.indexOf("/by") - 1 < 0) throw new DukeException("☹ OOPS!!! Please set date of deadline with /by.\n");
        String description = str.substring(type.length() + 1,str.indexOf("/by") - 1);
        String date = str.substring(str.indexOf("/by") + 4);
        String formattedDate = Parser.parseDate(date);
        return new Deadline(description,formattedDate);
      } else if (type.equals("event")) {
        if (splitStr.length < 2)throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.\n");
        if (str.indexOf("/at") - 1 < 0) throw new DukeException("☹ OOPS!!! Please set date of event with /at.\n");
        String description = str.substring(type.length() + 1,str.indexOf("/at") - 1);
        String date = str.substring(str.indexOf("/at") + 4);
        String formattedDate = Parser.parseDate(date);
        return new Event(description,formattedDate);
      } else {
        return null;
      }
  }
}
