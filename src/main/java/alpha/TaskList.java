package alpha;

import alpha.task.Task;

import java.util.List;

public class TaskList {
    protected enum TaskStatusAction {MARK, UNMARK};
    protected List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addToTaskList(Task task) {
        tasks.add(task);
    }

    public void printTasks(Ui uI) {
        if (tasks.isEmpty()) {
            uI.colouredPrint(uI.ANSI_BLUE,">> " + "Your alpha.task list is empty!");
        } else {
            uI.colouredPrint(uI.ANSI_BLUE,">> " + "Your alpha.task list is as follows:");
            int count = 1;
            for (Task task : tasks) {
                uI.colouredPrint(uI.ANSI_BLUE,count + ") " + task.toString());
                count++;
            }
        }
    }

    public void modifyTaskStatus(int taskNumeber, boolean taskStatus) {
            tasks.get(taskNumeber - 1).changeStatus(taskStatus);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }
}
