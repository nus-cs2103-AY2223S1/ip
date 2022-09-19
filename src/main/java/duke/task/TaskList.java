package duke.task;

import java.util.ArrayList;

/**
 * Class which manages the task list.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String showTask(int index) {
        return taskList.get(index - 1).getTask();
    }

    public void mark(int index) {
        taskList.get(index - 1).markAsDone();
    }

    public void unmark(int index) {
        taskList.get(index - 1).markAsUndone();
    }

    public void delete(int index) {
        taskList.remove(index - 1);
    }

    public void delete(Task task) {
        taskList.remove(task);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public int listSize() {
        return taskList.size();
    }

    /**
     * Displays the task list.
     * @return list of tasks
     */
    public String showTaskList() {
        if (taskList.size() == 0) {
            return "You have no tasks currently!";
        } else {
            String message = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                message = message + (i + 1) + ". " + taskList.get(i).getTask() + "\n";
            }
            return message.stripTrailing();
        }
    }
}
