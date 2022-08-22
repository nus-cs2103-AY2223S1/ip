package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exception.TaskIndexOutOfBoundsException;

public class TaskList {
    private List<Task> tasks;
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList() {
        tasks = new ArrayList<>();
    }
    
    public void add(Task task) {
        tasks.add(task);
    }
    
    public Task delete(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex - 1);
        tasks.remove(taskIndex);
        return task;
    }
    
    public Task mark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = tasks.get(taskIndex - 1);
        task.markAsDone();
        return task;
    }

    public Task unmark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = tasks.get(taskIndex - 1);
        task.unmarkAsDone();
        return task;
    }
    
    public int size() {
        return tasks.size();
    }
    
    public List<Task> getTasks() {
        return tasks;
    }
    
    public void listTasks() {
        int len = tasks.size();
        if (len == 0) {
            System.out.println("       YOU HAVE NO TASKS");
        } else {
            for (int i = 0; i < len; i++) {
                Task task = tasks.get(i);
                System.out.printf("   %d.%s%n", i + 1, task);
            }
        }
    }
    public boolean isValidIndex(int index) {
        return index >= 1 && index <= this.size();
    }
}
