package duke.tasklist;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.DukeTask;

import java.util.ArrayList;

/**
 * Encapsulates the list of items stored by Apollo.
 *
 * @author Kartikeya
 */
public class TaskList {
  // Stores the items given to Apollo
  private final ArrayList<DukeTask> items;

  public TaskList(ArrayList<DukeTask> items) {
    this.items = items;
  }

  public TaskList() {
    items = new ArrayList<>();
  }

  /**
   * Adds given DukeTask to the list of items.
   * @param newItem DukeTask that needs to be added to the list of items
   */
  public void add(DukeTask newItem) {
    items.add(newItem);
  }

  /**
   * Deletes an item from the items list.
   * @param i 1-indexed position of item to be deleted
   * @return string signifying successful deletion
   * @throws DukeException if the index is erroneous
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
   * @param i 1-indexed position of item to be marked
   * @return string signifying successful marking
   * @throws DukeException if the index is erroneous
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
   * @param i 1-indexed position of item to be marked
   * @return string signifying successful marking
   * @throws DukeException if the index is erroneous
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
   * @return string signifying number of items stored
   */
  public String listCount() {
    return "\nNow you have " + items.size() + " task" +
        (items.size() == 1 ? "" : "s") +" in the list.";
  }

  /**
   * Saves list of items to storage.
   * @param s storage object that is used to save
   */
  public void save(Storage s) {
    s.save(items);
  }

  /**
   * Returns string signifying the items stored in the list.
   * @return string signifying stored items
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
