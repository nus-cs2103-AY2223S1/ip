package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * Encapsulate a list that store all User's tasks.
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
     * Class constructor for ToDoList with an ArrayList argument.
     *
     * @param list arraylist of task.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.length = list.size();
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
     * @param taskNumber task number that user wants to mark as done.
     * @return the task that got unmarked.
     * @throws DukeException if item number is not valid.
     */
    public Task mark(int taskNumber) throws DukeException {
        try {
            list.get(taskNumber - 1).markAsDone();
            return list.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You do not have that item number!");
        }
    }

    /**
     * Marks item as not done in list.
     *
     * @param taskNumber task number that user wants to mark as not done.
     * @return the task that got unmarked.
     * @throws DukeException if task number is not valid.
     */
    public Task unmark(int taskNumber) throws DukeException{
        try {
            list.get(taskNumber - 1).markAsNotDone();
            return list.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You do not have that item number!");
        }
    }

    /**
     * Delete item for list.
     *
     * @param taskNumber item with the number user want to delete.
     * @return Task to be deleted.
     */
    public Task delete(int taskNumber) {
        Task taskToRemove = null;
        try {
            taskToRemove = list.get(taskNumber - 1);
            list.remove(taskNumber - 1);
            this.length -= 1;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You do not have that item number!");
        }
        return taskToRemove;
    }

    /**
     * Updates data file with all existing tasks.
     *
     * @param storage storage with path to data file.
     * @throws DukeException if something went wrong with the update.
     */
    public void updateStorage(Storage storage) throws DukeException {
        storage.update(this.list);
    }

    public int getLength() {
        return this.length;
    }

    public TaskList find(String userInput) {
        ArrayList<Task> currTasks = new ArrayList<>(this.list);
        currTasks.removeIf(x -> !x.getDescription().contains(userInput));
        return new TaskList(currTasks);
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
