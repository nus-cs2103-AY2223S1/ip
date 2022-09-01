package alpha;

import alpha.task.Task;

import java.time.DateTimeException;
import java.util.List;

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
     * Displays the list of tasks.
     *
     * @param uI Object of the Ui class to display the tasks.
     */
    public void printTasks(Ui uI){
        if (tasks.isEmpty()) {
            uI.colouredPrint(uI.ANSI_BLUE, ">> " + "Your task list is empty!");
        } else {
            uI.colouredPrint(uI.ANSI_BLUE, ">> " + "Your task list is as follows:");
            int count = 1;
            for (Task task : tasks) {
                uI.colouredPrint(uI.ANSI_BLUE, count + ") " + task.toString());
                count++;
            }
        }
    }

    /**
     * Modifies the status of the task at the provided index in the list.
     *
     * @param taskNumeber The task number of the task whose status needs to be modified.
     * @param taskStatus The status to which the task status needs to be modified.
     * @throws AlphaException If the task number is out of bounds of the task list.
     */
    public void modifyTaskStatus(int taskNumeber, boolean taskStatus) throws AlphaException {
        try {
            tasks.get(taskNumeber - 1).changeStatus(taskStatus);
        } catch (IndexOutOfBoundsException e) {
            throw new AlphaException("Invalid input: This task number doesn't exist!");
        }
    }

    /**
     * Deleted task of the provided task number from the task list.
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
}
