import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public void add(Task task) {
        this.tasks.add(task);
    }
    
    public Task delete(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return task;
    }
    
    public Task mark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex);
        task.markAsDone();
        return task;
    }

    public Task unmark(int taskIndex) throws TaskIndexOutOfBoundsException {
        if (!isValidIndex(taskIndex)) {
            throw new TaskIndexOutOfBoundsException(taskIndex, this.size());
        }
        Task task = this.tasks.get(taskIndex);
        task.unmarkAsDone();
        return task;
    }
    
    
    public int size() {
        return this.tasks.size();
    }
    
    public List<Task> getTasks() {
        return this.tasks;
    }
    
    public void listTasks() {
        int len = this.tasks.size();
        if (len == 0) {
            System.out.println("       YOU HAVE NO TASKS");
        } else {
            for (int i = 0; i < len; i++) {
                Task task = this.tasks.get(i);
                System.out.printf("   %d.%s%n", i + 1, task);
            }
        }
    }
    
    public boolean isValidIndex(int index) {
        return index >= 0 && index < this.size();
    }
}
