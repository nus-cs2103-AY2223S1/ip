package bobthebot.tasks;

import java.util.ArrayList;

import bobthebot.utils.Storage;
import bobthebot.utils.Ui;

/**
 * Class which represents a ToDoList.
 */
public class ToDoList {
    private ArrayList<Task> list;
    private Storage storage;

    /**
     * Constructs instance of ToDoList.
     *
     * @param list of tasks. List of tasks can be empty or contains elements taken from storage.
     * @param storage Storage from which
     */
    public ToDoList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }


    public void addTask(Task task) {
        list.add(task);
        storage.store(list);
    }

    /**
     * Deletes a specific event from the list, and updating the storage.
     *
     * @param index Specifies 0 index of task to be deleted.
     * @return Task deleted message.
     */
    public String deleteTask(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        storage.store(list);
        return Ui.taskDeletedMessage(task, this);
    }

    /**
     * Marks a specific event from the list as done, and updating the storage.
     *
     * @param index Specifies 1 index of task to be marked as done.
     * @return Task mark item done message.
     */
    public String markItemDone(int index) {
        this.list.get(index - 1).markDone();
        storage.store(list);
        return Ui.markItemDoneMessage(this, index - 1); // takes in 0 index
    }

    /**
     * Marks a specific event from the list as undone, and updating the storage.
     *
     * @param index Specifies 1 index of task to be marked as undone.
     * @return Task mark item undone message.
     */
    public String markItemUndone(int index) {
        this.list.get(index - 1).markUndone();
        storage.store(list);
        return Ui.markItemUndoneMessage(this, index - 1); // takes in 0 index
    }

    /**
     * Returns the number of items in the ToDo List.
     *
     * @return An int representing the number of items in the ToDo List.
     */
    public int getLength() {
        return this.list.size();
    }

    /**
     * Returns the specified task.
     *
     * @param index 0 index of the task specified.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    public ToDoList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.getLength(); i++) {
            if (this.getTask(i).toString().contains(keyword)) {
                matchingTasks.add(this.getTask(i));
            }
        }
        ToDoList listWithMatchingTasks = new ToDoList(matchingTasks, storage);
        return listWithMatchingTasks;
    }

    /**
     * Returns a String containing all the elements in the list.
     *
     * @return String containing all the elements in the list.
     */
    @Override
    public String toString() {
        int numOfElements = this.list.size();
        String res = "";
        for (int i = 1; i <= numOfElements; i++) {
            String curr = "\t" + i + ". " + this.list.get(i - 1).toString();
            if (i != numOfElements) {
                curr += "\n";
            }
            res += curr;
        }
        return res;
    }
}
