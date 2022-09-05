import java.util.ArrayList;

public class TaskList {

    public static ArrayList<String> TASK_SAVE_STRINGS;
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task markTask(int taskIndex) throws InvalidTaskNumberException {
        if (isInvalidTaskIndex(taskIndex)) {
            throw new InvalidTaskNumberException("mark");
        }
        this.tasks.get(taskIndex).mark();
        return this.tasks.get(taskIndex);
        // TODO Save task
        // TODO output task marked
    }

    public Task unmarkTask(int taskIndex) throws InvalidTaskNumberException {
        if (isInvalidTaskIndex(taskIndex)) {
            throw new InvalidTaskNumberException("unmark");
        }
        this.tasks.get(taskIndex).unmark();
        return this.tasks.get(taskIndex);
        // TODO Save task
        // TODO output task unmarked
    }

    public Task deleteTask(int taskIndex) throws InvalidTaskNumberException {
        if (isInvalidTaskIndex(taskIndex)) {
            throw new InvalidTaskNumberException("delete");
        }
        return this.tasks.remove(taskIndex);
    }

    private boolean isInvalidTaskIndex(int taskIndex) {
        return taskIndex < 0 || taskIndex >= this.tasks.size();
    }
}
