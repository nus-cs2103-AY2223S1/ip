import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void toggleTaskStatus(int index) {
        Task task = this.list.get(index);
        task.toggleStatus();
    }

    public void addTask(Duke.Commands type, String description) {
        Task task = new Task(description);
        list.add(task);
    }

    public void addTask(Duke.Commands type, String description, LocalDateTime time) {
        switch (type) {
            case DEADLINE:
                list.add(new Deadline(description, time));
                break;
            case EVENT:
                list.add(new Event(description, time));
                break;
        }
    }

    public void deleteTask(Integer index) {
        this.list.remove(index - 1);
    }

    @Override
    public String toString() {
        String result = "";
        int length = this.list.size();
        for (int i = 0; i < length; i++) {
            Task curr = this.list.get(i);
            result +=String.format("%d. %s \n",i + 1, curr.formatTask());
        }
        return result;
    }

    public Integer length() {
        return this.list.size();
    }

    public Task get(Integer index) {
        return this.list.get(index);
    }
}
