package duke.functions;

import duke.tasks.Task;

/**
 * Class which holds the list of tasks inputted by a user.
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
     * @return The Task array containing the tasks that has been inputted by the user.
     */
    public Task[] getTaskArr() {
        return this.taskArr;
    }

    /**
     * Marks a task as complete.
     * @param taskPos The position of the task which the user is marking as complete in the TaskList.
     */
    public void markComplete(int taskPos) {
        this.taskArr[taskPos].mark();
    }

    /**
     * Marks a task as incomplete.
     * @param taskPos The position of the task which the user is marking as incomplete in the TaskList.
     */
    public void markIncomplete(int taskPos) {
        this.taskArr[taskPos].unMark();
    }

    /**
     * Deletes a specific task from the Tasklist.
     * @param taskPos The position of the task which the user is deleting in the task array.
     * @param numberTasksLeft The number of tasks left in the task list after the specified task has been deleted.
     */
    public void deleteTask(int taskPos, int numberTasksLeft) {
        /**
         * Shifts tasks in task array behind the deleted task one unit
         * down to replace the deleted task.
         */
        Task.numberTasks = Task.getNumberTasks() - 1;
        for (int i = (taskPos - 1); i <= numberTasksLeft; i++) {
            this.taskArr[i] = this.taskArr[i + 1];
        }
    }

    /**
     * Adds a new task to the Tasklist.
     * @param newTask The Task which is being added to the TaskList.
     */
    public void addTask(Task newTask) {
        this.taskArr[Task.getNumberTasks()] = newTask;
    }
}
