package tasklist;
import java.util.ArrayList;
import java.util.List;

import exception.DukeException;
import task.Task;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public String listTask() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            output += String.format("\t%d.%s\n", i + 1, currTask);
        }
        return output;
    }

    public int numOfTasks() {
        return tasks.size();
    }

    public String addTask(Task task) {
        tasks.add(task);
        String output = String.format("\t    %s\n", task);
        return output;
    }

    public String deleteTask(int index) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("empty taskslist");
        }
        Task currTask = tasks.remove(index);
        String output = String.format("\t    %s\n", currTask);
        return output;
    }

    public String mark(int index) {
        Task currTask = tasks.get(index);
        currTask.mark();
        String message = String.format("\t    %s\n", currTask);
        return message;
    }

    public String unmark(int index) {
        Task currTask = tasks.get(index);
        currTask.unmark();
        String message = String.format("\t    %s\n", currTask);
        return message;
    }
}
