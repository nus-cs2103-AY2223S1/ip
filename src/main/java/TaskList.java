import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();

    public Task addTodo(String taskItem) {
        Task task = new Todo(taskItem);
        this.list.add(task);
        return task;
    }

    public Task addDeadline(String taskItem, String deadline) {
        Task task = new Deadline(taskItem, deadline);
        this.list.add(task);
        return task;
    }

    public Task addEvent(String taskItem, String eventTiming) {
        Task task = new Event(taskItem, eventTiming);
        this.list.add(task);
        return task;
    }

    @Override
    public String toString() {
        String res = TextFormatter.formatLine("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            String nextListItem = String.format("%d.%s", i + 1, this.list.get(i));
            res += TextFormatter.formatLine(nextListItem);
        }
        return res;
    }

    public Task markItem(int index) {
        Task taskItem = this.list.get(index - 1);
        taskItem.setIsMarked(true);
        return taskItem;
    }

    public Task unmarkItem(int index) {
        Task taskItem = this.list.get(index - 1);
        taskItem.setIsMarked(false);
        return taskItem;
    }

    public Task deleteItem(int index) {
        Task taskItem = this.list.get(index - 1);
        this.list.remove(index - 1);
        return taskItem;
    }

    public int getTaskCount() {
        return this.list.size();
    }
}