package duke.functions;
import java.util.Scanner;

import duke.support.Parser;
import duke.tasks.Task;

/**
 * Class which holds the list of tasks inputted by a user.
 *
 * @author lauralee
 */
public class TaskList {

    private Task[] taskArr;

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
     * Get Task at specified position of TaskList.
     *
     * @param taskPos The position of the Task in the array.
     * @return The Task at the specified position of the TaskList.
     */
    public Task getTask(int taskPos) {
        return getTaskArr()[taskPos];
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
     */
    public void deleteTask(int taskPos) {
        //Shifts tasks in task array behind the deleted task one unit
        //down to replace the deleted task.
        Task.deleteTask();
        for (int i = (taskPos - 1); i <= Task.getNumberTasks(); i++) {
            this.taskArr[i] = this.taskArr[i + 1];
        }
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param newTask The Task which is being added to the TaskList.
     */
    public void addTask(Task newTask) {
        this.taskArr[Task.getNumberTasks()] = newTask;
    }

    /**
     * Creates TaskList with Tasks in the TaskList created previously in Duke which has
     * been stored at the specified file location.
     *
     * @param scanner The scanner to scan the stored file with the previous TaskList.
     * @return The TaskList where Tasks from the previously created TaskList has been added.
     */
    public TaskList addFromStorage(Scanner scanner) {
        while (scanner.hasNext()) {
            Parser parser = new Parser(this);
            String task = scanner.nextLine();
            Task taskToBeAdded = parser.loadFromStorage(task);
            this.addTask(taskToBeAdded);
        }
        return this;
    }
}
