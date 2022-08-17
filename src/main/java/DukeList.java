import java.util.ArrayList;

public class DukeList {
  private final ArrayList<DukeTask> items;

  public DukeList() {
    items = new ArrayList<>();
  }

  public String addItem(String[] newItem) {
    String s = String.join(" ", newItem);
    if (newItem[0].equals("")) {
      return "";
    }
    DukeTask newTask;
    switch (newItem[0]) {
      case "todo": {
        newTask = new ToDo(s);
        items.add(newTask);
        return "Item added: " + newTask + listCount();
      }
      case "deadline": {
        String[] deadlineSplit = s.split(" /by ");
        newTask = new Deadline(deadlineSplit[0], deadlineSplit[1]);
        items.add(newTask);
        return "Item added: " + newTask + listCount();
      }
      case "event": {
        String[] deadlineSplit = s.split(" /at ");
        newTask = new Event(deadlineSplit[0], deadlineSplit[1]);
        items.add(newTask);
        return "Item added: " + newTask + listCount();
      }
      default:
        throw new IllegalStateException("Unexpected value: " + newItem[0]);
    }
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
    StringBuilder output = new StringBuilder("Your current tasks:\n");
    for (int i = 0; i < items.size(); i++) {
      output.append((i + 1) + ". " + items.get(i) + "\n");
    }
    return output.toString();
  }
}
