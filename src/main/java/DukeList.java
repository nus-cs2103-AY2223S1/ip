import java.util.ArrayList;

public class DukeList {
  private final ArrayList<DukeTask> items;

  public DukeList() {
    items = new ArrayList<>();
  }

  public String addItem(String[] newItem) throws DukeException {
    String s = String.join(" ", newItem);
    if (newItem[0].equals("")) {
      return "";
    }
    DukeTask newTask;
    switch (newItem[0]) {
      case "todo": {
        try {
          newTask = new ToDo(s.split("todo ")[1]);
          items.add(newTask);
          return "Item added: " + newTask + listCount();
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("A todo task's description cannot be empty.");
        }
      }
      case "deadline": {
        String[] deadlineSplit = s.split(" /by ");
        try {
          newTask = new Deadline(deadlineSplit[0].split("deadline ")[1],
              deadlineSplit[1]);
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("An incorrect deadline description was entered.");
        }
        items.add(newTask);
        return "Item added: " + newTask + listCount();
      }
      case "event": {
        String[] deadlineSplit = s.split(" /at ");
        try {
          newTask = new Event(deadlineSplit[0].split("event ")[1], deadlineSplit[1]);
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("An incorrect event description was entered.");
        }
        items.add(newTask);
        return "Item added: " + newTask + listCount();
      }
      default:
        throw new DukeException("\"" + newItem[0] + "\"" + " is not a recognised command.");
    }
  }

  public String deleteItem(int i) {
    DukeTask item = items.get(i - 1);
    items.remove(i - 1);
    return "\033[0;32mSuccess!\033[0m The following item has been deleted:\n"
        + item + listCount();
  }

  public String mark(int i) {
    return items.get(i - 1).markAsDone();
  }

  public String unmark(int i) {
    return items.get(i - 1).markAsNotDone();
  }

  public String listCount() {
    return "\nNow you have " + items.size() + " task" +
        (items.size() == 1 ? "" : "s") +" in the list.";
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder("Your current tasks:");
    for (int i = 0; i < items.size(); i++) {
      output.append("\n" + (i + 1) + ". " + items.get(i));
    }
    return output.toString();
  }
}
