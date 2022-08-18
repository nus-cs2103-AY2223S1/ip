import java.util.ArrayList;

/**
 * Encapsulates the list of items stored by Apollo.
 *
 * @author Kartikeya
 */
public class DukeList {
  // Stores the items given to Apollo
  private final ArrayList<DukeTask> items;

  public DukeList() {
    items = new ArrayList<>();
  }

  /**
   * Executes item addition commands, throwing a DukeException on incorrect
   * inputs.
   *
   * @param newItem Parsed item addition command
   * @return String signifying result of adding newItem to items
   * @throws DukeException Indicates incorrect inputs
   */
  public String addItem(String[] newItem) throws DukeException {
    String s = String.join(" ", newItem);
    if (newItem[0].equals("")) {
      return "";
    }
    DukeTask newTask;
    switch (newItem[0]) {
      case "todo": {
        try {
          // Obtain description of task
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
          // Obtain description of task
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
          // Obtain description of task
          newTask = new Event(deadlineSplit[0].split("event ")[1],
              deadlineSplit[1]);
        } catch (IndexOutOfBoundsException e) {
          throw new DukeException("An incorrect event description was entered.");
        }
        items.add(newTask);
        return "Item added: " + newTask + listCount();
      }
      default:
        throw new DukeException("\"" + newItem[0] + "\"" +
            " is not a recognised command.");
    }
  }

  /**
   * Deletes an item from the items list.
   *
   * @param i 1-indexed position of item to be deleted
   * @return String signifying successful deletion
   * @throws DukeException Indicates incorrect index
   */
  public String deleteItem(int i) throws DukeException {
    try {
      DukeTask item = items.get(i - 1);
      items.remove(i - 1);
      return "Success! The following item has been deleted:\n "
          + item + listCount();
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("This item does not exist.");
    }
  }

  /**
   * Marks an item as completed.
   *
   * @param i 1-indexed position of item to be marked
   * @return String signifying successful marking
   * @throws DukeException Indicates incorrect index
   */
  public String mark(int i) throws DukeException {
    try {
      return items.get(i - 1).markAsDone();
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("This item does not exist.");
    }
  }

  /**
   * Marks an item as not completed.
   *
   * @param i 1-indexed position of item to be marked
   * @return String signifying successful marking
   * @throws DukeException Indicates incorrect index
   */
  public String unmark(int i) throws DukeException {
    try {
      return items.get(i - 1).markAsNotDone();
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("This item does not exist.");
    }
  }

  /**
   * Returns number of items in the list as a string.
   *
   * @return String signifying number of items stored
   */
  public String listCount() {
    return "\nNow you have " + items.size() + " task" +
        (items.size() == 1 ? "" : "s") +" in the list.";
  }

  /**
   * Returns string signifying the items stored in the list.
   *
   * @return String signifying stored items
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder("Your current tasks:");
    for (int i = 0; i < items.size(); i++) {
      output.append("\n  " + (i + 1) + ". " + items.get(i));
    }
    return output.toString();
  }
}
