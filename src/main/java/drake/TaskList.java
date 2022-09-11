package drake;

import drake.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }


    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= list.size();
    }

    public void markAsDone(int taskNumber) {
        list.get(taskNumber - 1).markAsDone();
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

    public TaskList filter(List<String> searchKeywords) {
        TaskList result = new TaskList(new ArrayList<>());

        for (Task task : list) {
            if (task.isMatch(searchKeywords)) {
                result.addTask(task);
            }
        }

        return result;
    }
}
