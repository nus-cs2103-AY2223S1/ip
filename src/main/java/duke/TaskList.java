package duke;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Contains methods and attributes relevant to storing and operating
 * on tasks added by user.
 * 
 * @author Siau Wee
 */
public class TaskList {

    private ArrayList<Task> addedTasks = new ArrayList<>();
    
    /**
     * Returns the size of the task array.
     * 
     * @return Number of tasks in the array
     */
    public int getSize() {
        int sizeOfTaskList = this.addedTasks.size();
        assert sizeOfTaskList >= 0;
        return sizeOfTaskList;
    }

    /**
     * Returns the task at the specified array.
     */
    public Task getTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        return this.addedTasks.get(index);
    }

    /**
     * Loads a given task list into the actual program task list.
     * @param tasksToLoad The specified tasks to be loaded
     */
    public void loadTasks(ArrayList<Task> tasksToLoad) {
        this.addedTasks = tasksToLoad;
    }

    /**
     * Adds a given task to the task array.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null;
        assert addedTasks != null;
        this.addedTasks.add(task);
        Storage.saveToDirectory(this.addedTasks);
    }

    /**
     * Removes the task at the specified index from the array.
     *
     * @param index The index at which the contained task is to be deleted.
     * @throws TaskNotFoundException If there is no task at the specified index.
     */
    public Task deleteTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task removedTask = this.addedTasks.remove(index - 1);
        Storage.saveToDirectory(this.addedTasks);
        return removedTask;
    }

    /**
     * Searches for tasks in the task array with a specific sequence of chars
     * in their task name, then prints these tasks to output.
     * @param chars The sequence of chars to search tasks by
     */
    public ArrayList<String> findTask(String chars) {
        ArrayList<String> foundTasks = new ArrayList<>();
        for (int i = 0; i < this.getSize(); ++i) {
            Task searchedTask = this.addedTasks.get(i);
            if (searchedTask.doesNameContain(chars)) {
                foundTasks.add((i + 1) + ". " + searchedTask);
            }
        }
        return foundTasks;
    }

    /**
     * Marks the task at the specified index as done.
     * 
     * @param index The index at which the contained task is to be marked done.
     * @throws TaskNotFoundException If there is no task at the specified index.
     */
    public Task markTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task taskToMark = this.addedTasks.get(index - 1);
        taskToMark.mark();
        Storage.saveToDirectory(this.addedTasks);
        return taskToMark;
    }

    /**
     * Marks the task at the specified index as undone.
     * 
     * @param index The index at which the contained task is to be marked undone.
     * @throws TaskNotFoundException If there is no task at the specified index.
     */
    public Task unmarkTask(int index) throws TaskNotFoundException {
        if (index > this.getSize() || index < 0) {
            throw new TaskNotFoundException(String.valueOf(index));
        }
        Task taskToUnmark = this.addedTasks.get(index - 1);
        taskToUnmark.unmark();
        Storage.saveToDirectory(this.addedTasks);
        return taskToUnmark;
    }

    public void sortListByDateAscending() {
        this.addedTasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.compareTo(task2);
            }
        });
    }

    public void sortListByTaskNameAscending() {
        this.addedTasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.getTaskName().compareTo(task2.getTaskName());
            }
        });
    }

}
