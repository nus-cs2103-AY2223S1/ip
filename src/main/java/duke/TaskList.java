package duke;

import java.util.ArrayList;

/**
 * Contains methods and attributes relevant to storing and operating
 * on tasks added by user.
 * 
 * @author Siau Wee
 */
public class TaskList {

    private ArrayList<Task> addedTasks;

    private Storage storage;

    private Ui ui;

    /**
     * Constructor to initialise the TaskList with given arguments.
     * 
     * @param parser The Parser object
     * @param storage The Storage object
     * @param ui The Ui object
     */
    public TaskList(Parser parser, Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
        this.addedTasks = new ArrayList<>(100);
    }
    
    /**
     * Returns the size of the task array.
     * 
     * @return Number of tasks in the array
     */
    public int getSize() {
        return this.addedTasks.size();
    }
    
    /**
     * Adds a given task to the task array.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.addedTasks.add(task);
        this.storage.saveToDirectory(this.addedTasks);
        this.ui.taskAddedMessage(task);
        this.ui.taskListSizeMessage(this.getSize());
    }

    /**
     * Removes the task at the specified index from the array.
     * 
     * @param index The index at which the contained task is to be deleted.
     * @throws TaskNotFoundException If there is no task at the specified index.
     */
    public void deleteTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task removedTask = this.addedTasks.remove(index - 1);
        this.storage.saveToDirectory(this.addedTasks);
        this.ui.taskDeletedMessage(removedTask);
        this.ui.taskListSizeMessage(this.getSize());
    }

    /**
     * Prints all task to output.
     */
    public void listTask() {
        System.out.println("Listing your current tasks:");
        for (int i = 0; i < this.getSize(); ++i) {
            this.ui.printTaskWithIndex(this.addedTasks.get(i), i);
        }
    }

    /**
     * Searches for tasks in the task array with a specific sequence of chars
     * in their task name, then prints these tasks to output.
     * @param chars The sequence of chars to search tasks by
     */
    public void findTask(String chars) {
        Boolean isAnyTaskFound = false;
        System.out.println("Tasks matching your search term:");
        for (int i = 0; i < this.getSize(); ++i) {
            Task searchedTask = this.addedTasks.get(i);
            if (searchedTask.doesNameContain(chars)) {
                this.ui.printTaskWithIndex(searchedTask, i);
                isAnyTaskFound = true;
            }
        }
        if (!isAnyTaskFound) {
            System.out.println("No tasks found matching that search term.");
        }
    }

    /**
     * Marks the task at the specified index as done.
     * 
     * @param index The index at which the contained task is to be marked done.
     * @throws TaskNotFoundException If there is no task at the specified index.
     */
    public void markTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task taskToMark = this.addedTasks.get(index - 1);
        taskToMark.mark();
        this.storage.saveToDirectory(this.addedTasks);
        this.ui.taskMarkedMessage(taskToMark);
    }

    /**
     * Marks the task at the specified index as undone.
     * 
     * @param index The index at which the contained task is to be marked undone.
     * @throws TaskNotFoundException If there is no task at the specified index.
     */
    public void unmarkTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task taskToUnmark = this.addedTasks.get(index - 1);
        taskToUnmark.unmark();
        this.storage.saveToDirectory(this.addedTasks);
        this.ui.taskMarkedMessage(taskToUnmark);
    }
}
