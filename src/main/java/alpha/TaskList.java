package alpha;

import alpha.task.Task;

import java.time.DateTimeException;
import java.util.List;

public class TaskList {
    ;
    protected List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addToTaskList(Task task) {
        tasks.add(task);
    }

    public void printTasks(Ui uI) throws DateTimeException {
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

    public void modifyTaskStatus(int taskNumeber, boolean taskStatus) throws AlphaException {
        try {
            tasks.get(taskNumeber - 1).changeStatus(taskStatus);
        } catch (IndexOutOfBoundsException e) {
            throw new AlphaException("Invalid input: This task number doesn't exist!");
        }
    }

    public void deleteTask(int taskNumber) throws AlphaException {
        try {
            tasks.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new AlphaException("Invalid input: This task number doesn't exist!");
        }
    }
}
