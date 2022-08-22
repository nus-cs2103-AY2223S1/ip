import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getTaskListLength() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int index) {
        Task temp = this.taskList.get(index);
        this.taskList.remove(index);
        return temp;
    }

    public boolean getIsDone(int index) {
        return this.taskList.get(index).getIsDone();
    }

    public Task changeStatus(int index, boolean isDone) {
        this.taskList.get(index).changeStatus(isDone);
        return this.taskList.get(index);
    }

    public boolean isValidIndex(int index) {
        return index < this.taskList.size() && index >= 0;
    }
}
