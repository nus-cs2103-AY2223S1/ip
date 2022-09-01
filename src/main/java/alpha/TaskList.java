package alpha;

import alpha.task.Task;

import java.time.DateTimeException;
import java.util.ArrayList;
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

    public void printTasks(Ui uI) {
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
