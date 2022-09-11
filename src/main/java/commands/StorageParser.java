package commands;
import components.Deadline;
import components.Event;
import components.Storage;
import components.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class StorageParser {
  public static void parseToDo(String line, Storage storage) {
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
    }
  }

  public static void parseDeadline(String line, Storage storage) {
    try {
      if (line.contains("[X]")) {
        String var = line.substring(line.indexOf(":") + 2, line.length() - 1);
        Deadline test = new Deadline(line.substring(8, line.indexOf(":") - 4), LocalDate.parse(var));
        test.setStatus(true);
        storage.add(test);
      } else {
        String var = line.substring(line.indexOf(":") + 2, line.length() - 1);
        Deadline test = new Deadline(line.substring(8, line.indexOf(":") - 7), LocalDate.parse(var));
        storage.add(test);
      }
    } catch (DateTimeParseException e) {
      if (line.contains("[X]")) {
        Deadline test = new Deadline(line.substring(7, line.indexOf(":") - 4),
            line.substring(line.indexOf(":") + 2, line.length() - 1));
        test.setStatus(true);
        storage.add(test);
      } else {
        Deadline test = new Deadline(line.substring(7, line.indexOf(":") - 4),
            line.substring(line.indexOf(":") + 2, line.length() - 1));
        storage.add(test);
      }
    }
  }

  public static void parseEvent(String line, Storage storage) {
    try {
      if (line.contains("[X]")) {
        Event test = new Event(line.substring(7, line.indexOf(":") - 4),
            LocalDate.parse(line.substring(line.indexOf(":") + 2, line.length() - 1)));
        test.setStatus(true);
        storage.add(test);
      } else {
        Event test = new Event(line.substring(7, line.indexOf(":") - 4),
            LocalDate.parse(line.substring(line.indexOf(":") + 2, line.length() - 1)));
        storage.add(test);
      }
    } catch (DateTimeParseException e) {
      if (line.contains("[X]")) {
        Event test = new Event(line.substring(7, line.indexOf(":") - 4),
            line.substring(line.indexOf(":") + 2, line.length() - 1));
        test.setStatus(true);
        storage.add(test);
      } else {
        Event test = new Event(line.substring(7, line.indexOf(":") - 4),
            line.substring(line.indexOf(":") + 2, line.length() - 1));
        storage.add(test);
      }
    }
  }
}
