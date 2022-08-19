import java.io.IOException;
import java.util.ArrayList;

/**
 * Encapsulate the todolist that stores all user input.
 *
 * @author: Jonas Png
 */
public class TaskList {

    protected ArrayList<Task> list;

    protected int length;

    /**
     * Class constructor for ToDoList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.length = 0;
    }

    /**
     * Adds new item to list.
     *
     * @param item new list item to be added.
     */
    public void add(Task item) {
        list.add(item);
        this.length += 1;
    }

    /**
     * Marks item in list.
     *
     * @param itemNumber item with the number user want to mark as done.
     * @return the task that got unmarked.
     */
    public Task mark(int itemNumber) throws DukeException {
        try {
            list.get(itemNumber - 1).markAsDone();
            return list.get(itemNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You do not have that item number!");
        }
    }

    /**
     * Marks item as not done in list.
     *
     * @param itemNumber item with the number user want to mark as not done.
     * @return the task that got unmarked.
     */
    public Task unmark(int itemNumber) throws DukeException{
        try {
            list.get(itemNumber - 1).markAsNotDone();
            return list.get(itemNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You do not have that item number!");
        }
    }

    /**
     * Delete item for list
     *
     * @param itemNumber item with the number user want to delete.
     */
    public void delete(int itemNumber) {
        try {
            Task itemToRemove = list.get(itemNumber - 1);
            list.remove(itemNumber - 1);
            this.length -= 1;
            System.out.println("Noted. I've removed this task:");
            System.out.println(itemToRemove);
            System.out.println("Now you have " + this.length + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You do not have that item number!");
        }
    }

    public void updateStorage(Storage storage) throws DukeException {
        storage.update(this.list);
    }

    public int getLength() {
        return this.length;
    }

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder s = new StringBuilder();
        for (Task task : list) {
            s.append(counter + ". " + task.toString() + "\n");
            counter += 1;
        }
        return s.toString();
    }

}
