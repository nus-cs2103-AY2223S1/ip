import java.util.List;

public class TaskList {

    private final List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }


    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber <= list.size();
    }

    public void markAsDone(int taskNumber) {
        list.get(taskNumber - 1).mark();
    }

    public String getTaskToString(int taskNumber) {
        return list.get(taskNumber - 1).toString();
    }

    public void markAsNotDone(int taskNumber) {
        list.get(taskNumber - 1).markAsNotDone();
    }

    public void removeTask(int taskNumber) {
        list.remove(taskNumber - 1);
    }

    public String getSizeToString() {
        return "You now have " + list.size() + " tasks in the list";
    }

    public Task addTask(Task task) {
        list.add(task);
        return task;
    }
}
