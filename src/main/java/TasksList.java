import java.util.ArrayList;
import java.util.List;

public class TasksList {
    private List<Task> listOfTasks;

    public TasksList() {
        this.listOfTasks = new ArrayList<>();
    }

    public void addToList(Task task) {
        this.listOfTasks.add(task);
    }

    public Task markAsDone(int taskNumber) {
        Task taskToMark = this.listOfTasks.get(taskNumber - 1);
        taskToMark.markAsDone();
        return taskToMark;
    }

    public Task markAsUndone(int taskNumber) {
        Task taskToMark = this.listOfTasks.get(taskNumber - 1);
        taskToMark.markAsUndone();
        return taskToMark;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list: \n");
        for (int i = 1; i <= this.listOfTasks.size(); i++) {
            sb.append("\n");
            sb.append(i + ". " + this.listOfTasks.get(i - 1));
        }
        return sb.toString();
    }

    public int getLength() {
        return this.listOfTasks.size();
    }








}
