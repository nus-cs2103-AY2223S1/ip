package duke.functions;

import duke.tasks.Task;

/**
 * Class which holds the list of tasks inputted by a user.
 *
 * @author lauralee
 */
public class TaskList {

    Task[] taskArr;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.taskArr = new Task[100];
    }

    /**
     * Getter for TaskList which stores tasks inputted by the user.
     *
     * @return The Task array containing the tasks that has been inputted by the user.
     */
    public Task[] getTaskArr() {
        return this.taskArr;
    }

    /**
     * Marks a task as complete.
     *
     * @param taskPos The position of the task which the user is marking as complete in the TaskList.
     * @return The description returned by Duke when a task is marked.
     */
    public String markComplete(int taskPos) {
        return this.taskArr[taskPos].mark();
    }

    /**
     * Marks a task as incomplete.
     *
     * @param taskPos The position of the task which the user is marking as incomplete in the TaskList.
     * @return The description returned by Duke when a task is unmarked.
     */
    public String markIncomplete(int taskPos) {
        return this.taskArr[taskPos].unMark();
    }

    /**
     * Deletes a specific task from the TaskList.
     *
     * @param taskPos The position of the task which the user is deleting in the task array.
     * @param deletedTask The task specified by the user to be deleted from the TaskList.
     * @return The description returned by Duke when a task is deleted.
     */
    public String deleteTask(int taskPos, Task deletedTask) {
        /**
         * Shifts tasks in task array behind the deleted task one unit
         * down to replace the deleted task.
         */
        Task.deleteTask();
        for (int i = (taskPos - 1); i <= Task.getNumberTasks(); i++) {
            this.taskArr[i] = this.taskArr[i + 1];
        }
        return Ui.printDelete(deletedTask, Task.getNumberTasks());
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param newTask The Task which is being added to the TaskList.
     */
    public void addTask(Task newTask) {
        this.taskArr[Task.getNumberTasks()] = newTask;
    }
}
