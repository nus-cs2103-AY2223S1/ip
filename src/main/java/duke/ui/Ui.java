package duke.ui;

import duke.common.Message;
import duke.data.TaskList;
import duke.tasks.Task;

import java.util.Scanner;

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
        show(Message.GREETING);
    }

    public void showGoodbye() {
        show(Message.GOODBYE);
        this.in.close();
    }

    public void showTaskAdded(Task task) {
        show(Message.TASK_ADDED + task);
    }

    public void showTaskRemoved(Task task) {
        show(Message.TASK_REMOVED + task);
    }

    public void showTaskDone(Task task) {
        show(Message.TASK_DONE + task);
    }

    public void showTaskNotDone(Task task) {
        show(Message.TASK_NOT_DONE + task);
    }

    public void showNumberOfTasks(int count) {
        show(String.format(Message.NUMBER_OF_TASKS, count));
    }

    public void showAllTasks(TaskList taskList) {
        int size = taskList.numTasks();
        if (size != 0) {
            show(Message.TASK_LIST);
            for (int i = 0; i < size; i++) {
                int taskNum = i + 1;
                Task task = taskList.getTask(i);
                show(taskNum + "." + task);
            }
        } else {
            show(Message.EMPTY_TASK_LIST);
        }
    }
}
