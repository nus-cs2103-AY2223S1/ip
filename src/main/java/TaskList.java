import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    public Task mark(int index) {
        tasks.get(index).isDone = true;
        return tasks.get(index);
    }

    public Task unmark(int index) {
        tasks.get(index).isDone = false;
        return tasks.get(index);
    }

    public ArrayList<Task> listOfTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}
