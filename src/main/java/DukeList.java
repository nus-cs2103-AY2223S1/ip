public class DukeList {
  private final String[] items;
  private int index = 0;

  public DukeList() {
    items = new String[100];
  }

  public String addItem(String newItem) {
    if (newItem.equals("")) {
      return "";
    }
    items[index] = newItem;
    index++;
    return "Item added: " + newItem;
  }

  public String getList() {
    StringBuilder output = new StringBuilder("Your current tasks:\n");

    for (int i = 0; i < index; i++) {
      output.append((i + 1) + ". " + items[i] + "\n");
    }

    return output.toString();
  }
}
