package Duke;
import java.util.ArrayList;
import Duke.tasks.Task;

import java.util.List;

public class TaskList {

    private final List<Task> list;


    /**
     * Class Constructor to load tasklist from list input.
     */

    public TaskList(List<Task> list) {
        this.list = list;

    }

    /**
     * Class Constructor to initialises the task list with an empty list.
     */

    public TaskList() {
        list = new ArrayList<>();
    }

    public int getTaskListSize() {
        return list.size();
    }

    public Task getTask(int i) {
        return list.get(i);
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber <= list.size() && taskNumber >= 1;
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

    public TaskList filter(List<String> Keywords) {
        TaskList result = new TaskList(new ArrayList<>());

        for (Task task : list) {
            if (task.isMatching(Keywords)) {
                result.addTask(task);
            }
        }

        return result;
    }
}