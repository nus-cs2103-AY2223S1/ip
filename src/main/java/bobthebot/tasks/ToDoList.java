package bobthebot.tasks;

import java.util.ArrayList;

import bobthebot.utils.Storage;

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

    /**
     * Adds a given event from to list, and updates the storage.
     *
     * @param task Specifies 0 index of task to be deleted.
     */
    public void addTask(Task task) {
        list.add(task);
        storage.store(list);
    }

    /**
     * Deletes a specific event from the list, and updates the storage.
     *
     * @param index Specifies 0 index of task to be deleted.
     */
    public void deleteTask(int index) {
        this.list.remove(index);
        storage.store(list);
    }

    /**
     * Marks a specific event from the list as done, and updates the storage.
     *
     * @param index Specifies 1 index of task to be marked as done.
     */
    public void markItemDone(int index) {
        this.list.get(index - 1).markDone();
        storage.store(list);
    }

    /**
     * Marks a specific event from the list as undone, and updating the storage.
     *
     * @param index Specifies 1 index of task to be marked as undone.
     */
    public void markItemUndone(int index) {
        this.list.get(index - 1).markUndone();
        storage.store(list);
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

    /**
     * Finds the tasks that match a given keyword and puts all tasks in a ToDoList.
     *
     * @param keyword Keyword that tasks need to match.
     * @return ToDoList containing all matching tasks.
     */
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
     * Returns a ToDoList of deadlines due or events happening within the next week.
     *
     * @return ToDoList of deadlines due or events happening within the next week.
     */
    public ToDoList tasksDueSoon() {
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task t : list) {
            if ((t instanceof Deadline) && ((Deadline) t).isWithinWeekOfDeadline()) {
                taskList.add(t);
            }

            if ((t instanceof Event) && ((Event) t).isWithinWeekOfEvent()) {
                taskList.add(t);
            }
        }

        ToDoList tasksDueSoon = new ToDoList(taskList, storage);
        return tasksDueSoon;
    }

    /**
     * Returns a String containing all the elements in the list.
     *
     * @return String containing all the elements in the list.
     */
    @Override
    public String toString() {
        int numOfElements = this.list.size();
        String listString = "";
        for (int i = 1; i <= numOfElements; i++) {
            String curr = "\t" + i + ". " + this.list.get(i - 1).toString();
            if (i != numOfElements) {
                curr += "\n";
            }
            listString += curr;
        }
        return listString;
    }
}
