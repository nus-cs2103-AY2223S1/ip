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

    public void add(DukeTask newItem) {
        items.add(newItem);
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
                (items.size() == 1 ? "" : "s") + " in the list.";
    }

    public void save(Storage s) {
        s.save(items);
    }

    public ArrayList<DukeTask> find(String check) {
        ArrayList<DukeTask> tasks = new ArrayList<>();
        for (DukeTask t : items) {
            if (t.getDescription().contains(check)) {
                tasks.add(t);
            }
        }
        return tasks;
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
