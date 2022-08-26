import java.util.List;

public class TaskList {
    private Db db;
    private List<Task> tasks;

    public TaskList(Db db, List<Task> persistedTasks) {
        this.db = db;
        this.tasks = persistedTasks;
    }

    public void add(Task task) {
        tasks.add(task);
        db.save(tasks);
    }

    public String delete(int taskId) {
        Task task = tasks.get(taskId);
        tasks.remove(taskId);
        db.save(tasks);
        return task.toString();
    }

    public String markAsDone(int taskId) {
        Task task = tasks.get(taskId);
        task.markAsDone();
        db.save(tasks);
        return task.toString();
    }

    public String markAsUndone(int taskId) {
        Task task = tasks.get(taskId);
        task.markAsUndone();
        db.save(tasks);
        return task.toString();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You have no tasks currently.";
        }
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
