public class DukeList {
  private final DukeTask[] items;
  private int index = 0;

  public DukeList() {
    items = new DukeTask[100];
  }

  public String addItem(String newItem) {
    if (newItem.equals("")) {
      return "";
    }
    items[index] = new DukeTask(newItem);
    index++;
    return "Item added: " + newItem;
  }

  public String mark(int i) {
    items[i - 1].markAsDone();
    return "Good job! This task has been completed:\n"
        + items[i - 1];
  }

  public String unmark(int i) {
    items[i - 1].markAsNotDone();
    return "Whoops! This task is yet to be completed:\n"
        + items[i - 1];
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder("Your current tasks:\n");
    for (int i = 0; i < index; i++) {
      output.append((i + 1) + ". " + items[i] + "\n");
    }
    return output.toString();
  }

}
