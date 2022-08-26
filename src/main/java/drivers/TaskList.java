package drivers;

import exceptions.NoTaskException;
import exceptions.OutOfBoundsException;
import exceptions.TumuException;
import tasks.Task;

import java.util.List;

/**
 * Contains the task list e.g., it has operations
 * to add/delete tasks in the list.
 */
public class TaskList {
    private final List<Task> userTasks;

    /**
     * Constructor for the TaskList class.
     * @param userTasks List of tasks to be added into the TaskList class.
     */
    public TaskList(List<Task> userTasks) {
        this.userTasks = userTasks;
    }

    /**
     * Gets all the tasks within TaskList.
     * @return All the tasks within TaskList.
     */
    public List<Task> getTasks() {
        return userTasks;
    }

    /**
     * Gets the number of items in the list.
     * @return Number of items in the list.
     */
    public int getListSize() {
        return userTasks.size();
    }

    /**
     * Formats the task to be properly printed to the user.
     * @param taskPrint Empty list to contain the output for each task in the list.
     */
    public void fillTaskPrint(List<String> taskPrint) {
        for (int i = 1; i <= userTasks.size(); i++) {
            Task task = userTasks.get(i - 1);
            String output = String.format(" %d. %s", i, task);
            taskPrint.add(output);
        }
    }

    public Task markTask(int oneIndexedNum) throws TumuException {
        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) throw new NoTaskException();
            else throw new OutOfBoundsException(userTasks.size());
        } else {
            Task task = userTasks.get(oneIndexedNum - 1);
            task.markDone();
            return task;
        }
    }

    public Task unmarkTask(int oneIndexedNum) throws TumuException {
        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) throw new NoTaskException();
            else throw new OutOfBoundsException(userTasks.size());
        } else {
            Task task = userTasks.get(oneIndexedNum - 1);
            task.unmarkDone();
            return task;
        }
    }

    public Task deleteTask(int oneIndexedNum) throws TumuException {
        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) {
                throw new NoTaskException();
            } else {
                throw new OutOfBoundsException(userTasks.size());
            }
        } else {
            return userTasks.remove(oneIndexedNum - 1);
        }
    }

    public void addTask(Task task) {
        userTasks.add(task);
    }
}
