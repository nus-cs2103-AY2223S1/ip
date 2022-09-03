package duke;

import java.util.ArrayList;

/**
 * Encapsulate a list that store all User's tasks.
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    protected int length;

    /**
     * Class constructor for ToDoList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.length = 0;
    }

    /**
     * Class constructor for ToDoList with an ArrayList argument.
     *
     * @param tasks arraylist of task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.length = tasks.size();
    }

    /**
     * Adds new item to list.
     *
     * @param item new list item to be added.
     */
    public void add(Task item) {
        tasks.add(item);
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
            tasks.get(taskNumber - 1).markAsDone();
            return tasks.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number!");
        }
    }

    /**
     * Marks item as not done in list.
     *
     * @param taskNumber task number that user wants to mark as not done.
     * @return the task that got unmarked.
     * @throws DukeException if task number is not valid.
     */
    public Task unmark(int taskNumber) throws DukeException {
        try {
            tasks.get(taskNumber - 1).markAsNotDone();
            return tasks.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number!");
        }
    }

    /**
     * Delete item for list.
     *
     * @param taskNumber item with the number user want to delete.
     * @return Task to be deleted.
     * @throws DukeException if task number not valid.
     */
    public Task delete(int taskNumber) throws DukeException {
        assert taskNumber > 0;
        Task taskToRemove = null;
        try {
            taskToRemove = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            this.length -= 1;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number!");
        }
        assert taskToRemove != null;
        return taskToRemove;
    }

    /**
     * Updates data file with all existing tasks.
     *
     * @param storage storage with path to data file.
     * @throws DukeException if something went wrong with the update.
     */
    public void updateStorage(Storage storage) throws DukeException {
        storage.update(this.tasks);
    }

    public int getLength() {
        return this.length;
    }

    /**
     * Finds all tasks with description that matches user input.
     *
     * @param userInput String that user wants to match with task's description.
     * @return TaskList that contains tasks that matches user search description.
     */
    public TaskList find(String userInput) {
        ArrayList<Task> currTasks = new ArrayList<>(this.tasks);
        currTasks.removeIf(x -> !x.getDescription().contains(userInput));
        return new TaskList(currTasks);
    }

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            s.append(counter + ". " + task.toString() + "\n");
            counter += 1;
        }
        return s.toString();
    }

}
