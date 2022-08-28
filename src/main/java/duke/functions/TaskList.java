package duke.functions;

import duke.tasks.Task;

/**
 * Class which holds the list of tasks inputted by a user.
 * @author lauralee
 */
public class TaskList {

    Task[] taskArr;

    public TaskList() {
        this.taskArr = new Task[100];
    }

    public Task[] getTaskArr() {
        return this.taskArr;
    }

    public void markComplete(int taskPos) {
        this.taskArr[taskPos].mark();
    }

    public void markIncomplete(int taskPos) {
        this.taskArr[taskPos].unMark();
    }

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

    public void addTask(Task newTask) {
        this.taskArr[Task.getNumberTasks()] = newTask;
    }
}
