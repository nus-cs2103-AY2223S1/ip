package tako;

import tako.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void mark(int taskNumber) throws TakoException {
        if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
            throw new TakoException("The task number to mark does not exist.");
        }
        Task task = tasks.get(taskNumber);
        task.markAsDone();
    }

    public Task remove(int taskNumber) throws TakoException{
        if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
            throw new TakoException("The task number to delete does not exist.");
        }
        return tasks.remove(taskNumber);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getSize() {
        return tasks.size();
    }
}
