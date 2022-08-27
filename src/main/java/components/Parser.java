package components;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser, which tries to understand user input.
 */
public class Parser {

  private static Ui Ui;
  private static TaskList taskList;
  private static Storage storage;

  /**
   * Connects Ui object with Parser.
   *
   * @param ui Ui to be connected with Parser.
   */
  public static void setUi(Ui ui) {
    Parser.Ui = ui;
  }

  /**
   * Connects TaskList object with Parser.
   *
   * @param taskList taskList to be connected with Parser.
   */
  public static void setTaskList(TaskList taskList) {
    Parser.taskList = taskList;
  }

  /**
   * Parses and trying to make sense of user input.
   *
   * @param line string representation of user input.
   * @throws DukeException If user input is incoherent.
   */
  public static void parseLine(String line) throws DukeException {
    if (line.equals("bye")) {
      System.out.println("Bye. Hope to see you again soon!");
      System.exit(0);
    } else if (line.equals("list")) {
      //System.out.println("Here are the tasks in your list:");
      taskList.showTasks();

    } else if (line.contains("unmark")) {
      if (line.equals("unmark")) {
        throw new DukeException("☹ OOPS!!! The description of a mark cannot be empty.");
      } else {
        int num = Integer.parseInt(line.substring(7));
        taskList.setTaskStatus(num - 1, false);
        //System.out.println("OK, I've marked this task as not done yet:");
        //System.out.println(ls.get(num - 1).toString());
      }
    } else if (line.contains("mark")) {
      if (line.equals("mark")) {
        throw new DukeException("☹ OOPS!!! The description of a mark cannot be empty.");
      } else {
        int num = Integer.parseInt(line.substring(5));
        taskList.setTaskStatus(num - 1, true);
        //System.out.println("Nice! I've marked this task as done:");
        //System.out.println(ls.get(num - 1).toString());

      }
    } else if (line.contains("todo")) {
      if (line.equals("todo")) {
        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
      } else {
        String d1 = line.substring(5);
        Todo test = new Todo(d1);
        taskList.add(test);
      }
    } else if (line.contains("deadline")) {
      if (line.equals("deadline")) {
        throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");
      } else {
        try {
          String description = line.substring(9, line.indexOf("/") - 1);
          String var = line.substring(line.indexOf("/") + 4, line.length());
          LocalDate d1 = LocalDate.parse(var);
          Deadline test = new Deadline(description, d1);
          taskList.add(test);
        } catch (DateTimeParseException e) {
          String description = line.substring(9, line.indexOf("/") - 1);
          String by = line.substring(line.indexOf("/") + 4, line.length());
          String time = line.substring(line.length() - 4, line.length());
          Deadline test = new Deadline(description, by);
          taskList.add(test);
        }

      }
    } else if (line.contains("event")) {
      if (line.equals("event")) {
        throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");

      } else {
        try {
          String description = line.substring(6, line.indexOf("/") - 1);
          LocalDate d1 = LocalDate.parse(line.substring(line.indexOf("/") + 4));
          Event test = new Event(description, d1);
          taskList.add(test);

        } catch (DateTimeParseException e) {
          String description = line.substring(6, line.indexOf("/") - 1);
          String at = line.substring(line.indexOf("/") + 4);
          Event test = new Event(description, at);
          taskList.add(test);
        }
      }
    } else if (line.contains("delete")) {
      if (line.equals("delete")) {
        throw new DukeException("☹ OOPS!!! The description of a delete cannot be empty.");

      } else {
        int removal = Integer.parseInt(line.substring(7));
      }

    } else {
      throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
    }
  }

  public static void parseLineStorage(String line, Storage storage) {
    if (line.contains("[T]")) {
      if (line.contains("[X]")) {
        String d1 = line.substring(7);
        Todo test = new Todo(d1);
        test.setStatus(true);
        storage.add(test);
      } else {
        String d1 = line.substring(7);
        Todo test = new Todo(d1);
        storage.add(test);
      }

    } else if (line.contains("[D]")) {
      try {
        if (line.contains("[X]")) {
          String description = line.substring(7, line.indexOf(":") - 4);
          String var = line.substring(line.indexOf(":") + 2, line.length() - 1);
          LocalDate d1 = LocalDate.parse(var);
          Deadline test = new Deadline(description, d1);
          test.setStatus(true);
          storage.add(test);
        } else {
          String description = line.substring(7, line.indexOf(":") - 7);
          String var = line.substring(line.indexOf(":") + 2, line.length() - 1);
          LocalDate d1 = LocalDate.parse(var);
          Deadline test = new Deadline(description, d1);
          storage.add(test);
        }
      } catch (DateTimeParseException e) {
        if (line.contains("[X]")) {
          String description = line.substring(7, line.indexOf(":") - 4);
          String by = line.substring(line.indexOf(":") + 2, line.length() - 1);
          Deadline test = new Deadline(description, by);
          test.setStatus(true);
          storage.add(test);
        } else {
          String description = line.substring(7, line.indexOf(":") - 4);
          String by = line.substring(line.indexOf(":") + 2, line.length() - 1);
          Deadline test = new Deadline(description, by);
          storage.add(test);
        }
      }
    } else if (line.contains("[E]")) {
      try {
        if (line.contains("[X]")) {
          String description = line.substring(7, line.indexOf(":") - 4);
          LocalDate d1 = LocalDate.parse(line.substring(line.indexOf(":") + 2, line.length() - 1));
          Event test = new Event(description, d1);
          test.setStatus(true);
          storage.add(test);
        } else {
          String description = line.substring(7, line.indexOf(":") - 4);
          LocalDate d1 = LocalDate.parse(line.substring(line.indexOf(":") + 2, line.length() - 1));
          Event test = new Event(description, d1);
          storage.add(test);
        }
      } catch (DateTimeParseException e) {
        if (line.contains("[X]")) {
          String description = line.substring(7, line.indexOf(":") - 4);
          String at = line.substring(line.indexOf(":") + 2, line.length() - 1);
          Event test = new Event(description, at);
          test.setStatus(true);
          storage.add(test);
        } else {
          String description = line.substring(7, line.indexOf(":") - 4);
          String at = line.substring(line.indexOf(":") + 2, line.length() - 1);
          Event test = new Event(description, at);
          storage.add(test);
        }
      }
    }
  }

}


