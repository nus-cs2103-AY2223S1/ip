import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    void add(Task task) {
        this.tasks.add(task);
    }

    void delete(int num) {
        this.tasks.remove(num);
    }

    Task getTask(int num) {
        return this.tasks.get(num);
    }

    void read() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < size(); i++) {
            System.out.println(i + 1 + ". " + getTask(i));
        }
    }

    int size() {
        return this.tasks.size();
    }

    List<Task> list() {
        return this.tasks;
    }
}
