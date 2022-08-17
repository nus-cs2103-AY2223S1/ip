import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> data) {
        this.tasks = new ArrayList<>();
        for (String entry : data) {
            this.tasks.add(Task.parse(entry));
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public List<String> stringify() {
        List<String> list = new ArrayList<>();
        for (Task task : this.tasks) {
            list.add(task.stringify());
        }
        return list;
    }

    public String markTask(int index) {
        return this.tasks.get(index - 1).mark();
    }

    public String unmarkTask(int index) {
        return this.tasks.get(index - 1).unmark();
    }

    public String deleteTask(int index) {
        Task task = this.tasks.remove(index - 1);
        return task.toString();
    }

    public String addToDo(String description) {
        ToDo todo = new ToDo(description);
        this.addToTasks(todo);
        return todo.toString();
    }

    public String addDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        this.addToTasks(deadline);
        return deadline.toString();
    }

    public String addEvent(String description, LocalDate at) {
        Event event = new Event(description, at);
        this.addToTasks(event);
        return event.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append(String.format("%d. %s\n", 1 + i, this.tasks.get(i).toString()));
        }
        return result.toString();
    }

    private void addToTasks(Task task) {
        this.tasks.add(task);
    }
}
