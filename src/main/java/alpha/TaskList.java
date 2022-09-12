package alpha;

import java.util.ArrayList;
import java.util.List;

import alpha.task.Task;

/**
 * Operates on the list of tasks.
 */
public class TaskList {

    /** List of tasks to be operated on */
    protected List<Task> tasks;

    /**
     * Constructor to initialise all the global variables.
     *
     * @param tasks To initialise the list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the task list.
     *
     * @param task Task to be added to the list.
     */
    public void addToTaskList(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the list of tasks.
     *
     * @param uI Object of the Ui class to display the tasks.
     * @return A string containing a list of tasks.
     */
    public String printTasks(Ui uI) {
        if (tasks.isEmpty()) {
            return uI.returnText(">> " + "Your task list is empty!");
        } else {
            String tasksToBePrinted = "";
            tasksToBePrinted += uI.returnText(">> "
                    + "Your task list is as follows:\n");
            int count = 1;
            for (Task task : tasks) {
                tasksToBePrinted += uI.returnText(count + ") "
                        + task.toString() + "\n");
                count++;
            }
            return tasksToBePrinted;
        }
    }

    /**
     * Modifies the status of the task at the provided index in the list.
     *
     * @param taskNumber The task number of the task whose status needs to be modified.
     * @param taskStatus The status to which the task status needs to be modified.
     * @throws AlphaException If the task number is out of bounds of the task list.
     */
    public void modifyTaskStatus(int taskNumber, boolean taskStatus) throws AlphaException {
        try {
            tasks.get(taskNumber - 1).changeStatus(taskStatus);
        } catch (IndexOutOfBoundsException e) {
            throw new AlphaException("Invalid input: This task number doesn't exist!");
        }
    }

    /**
     * Deletes task of the provided task number from the task list.
     *
     * @param taskNumber The task number of the task that needs to be deleted.
     * @throws AlphaException If the task number is out of bounds.
     */
    public void deleteTask(int taskNumber) throws AlphaException {
        try {
            tasks.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new AlphaException("Invalid input: This task number doesn't exist!");
        }
    }

    /**
     * Filters the list of tasks and returns all tasks that contains the given keyword.
     *
     * @param keyword Keyword used to filter the list of tasks.
     * @return List of all tasks containing the keyword.
     */
    public List<Task> filterTaskDescription(String keyword) {
        List<Task> filteredTaskList = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }
}
