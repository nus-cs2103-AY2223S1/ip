import java.util.ArrayList;

public class DukeList {
  private final ArrayList<DukeTask> items;

  public DukeList() {
    items = new ArrayList<>();
  }

  public String addItem(String newItem) {
    if (newItem.equals("")) {
      return "";
    }
    items.add(new DukeTask(newItem));
    return "Item added: " + newItem;
  }

  public String mark(int i) {
    items.get(i - 1).markAsDone();
    return "Good job! This task has been completed:\n"
        + items.get(i - 1);
  }

  public String unmark(int i) {
    items.get(i - 1).markAsNotDone();
    return "Whoops! This task is yet to be completed:\n"
        + items.get(i - 1);
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
