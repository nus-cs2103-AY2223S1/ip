package duke.ui;

import java.util.Scanner;

import duke.common.Messages;
import duke.data.TaskList;
import duke.tasks.Task;

public class Ui {
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readInput() {
        return in.nextLine();
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void showGreeting() {
        show(Messages.MESSAGE_GREETING);
    }

    public void showGoodbye() {
        show(Messages.MESSAGE_GOODBYE);
        this.in.close();
    }

    public void showTaskAdded(Task task) {
        show(Messages.MESSAGE_TASK_ADDED + task);
    }

    public void showTaskRemoved(Task task) {
        show(Messages.MESSAGE_TASK_REMOVED + task);
    }

    public void showTaskDone(Task task) {
        show(Messages.MESSAGE_TASK_DONE + task);
    }

    public void showTaskNotDone(Task task) {
        show(Messages.MESSAGE_TASK_NOT_DONE + task);
    }

    public void showNumberOfTasks(int count) {
        show(String.format(Messages.MESSAGE_NUMBER_OF_TASKS, count));
    }

    public void showAllTasks(TaskList taskList) {
        int size = taskList.numTasks();
        if (size != 0) {
            show(Messages.MESSAGE_TASK_LIST);
            for (int i = 0; i < size; i++) {
                int taskNum = i + 1;
                Task task = taskList.getTask(i);
                show(taskNum + "." + task);
            }
        } else {
            show(Messages.MESSAGE_EMPTY_TASK_LIST);
        }
    }
}
