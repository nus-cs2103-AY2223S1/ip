import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void loadTask(Task task) {
        tasks.add(task);
    }

    public Task markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    private Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task addTodo(String description) {
        Todo todo = new Todo(description);
        return addTask(todo);
    }

    public Task addDeadline(String description, String date, String time) {
        Deadline deadline = new Deadline(description, date, time);
        return addTask(deadline);
    }

    public Task addEvent(String description,
                          String dateStart, String timeStart,
                          String dateEnd, String timeEnd) {
        Event event = new Event(description, dateStart, timeStart, dateEnd, timeEnd);
        return addTask(event);
    }
}
