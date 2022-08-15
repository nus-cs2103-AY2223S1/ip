import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Task> taskList = new ArrayList<>();
    int count = 0;

    public void add(Task task) {
        taskList.add(task);
        count++;
        System.out.println("added: " + task.getDescription());
    }

    public void iterate() {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task task : taskList) {
            System.out.println(i + "." + task.toString());
            i++;
        }
    }

    public void mark(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void unmark(int index) {
        this.taskList.get(index).markAsNotDone();
    }
}
