package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public ArrayList<Task> getArray() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int integer) {
        tasks.remove(integer - 1);
    }

    public Task latestTask() {
        return tasks.get(tasks.size() - 1);
    }

    public int taskListSize() {
        return tasks.size();
    }

    public Task getTask(int integer) {
        return tasks.get(integer);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void markAsDone(int integer) {
        tasks.get(integer - 1).markAsDone();
    }

    public void markAsNotDone(int integer) {
        tasks.get(integer - 1).markAsNotDone();
    }

    /**
     * Creates a taskList containing tasks with the keyword string found inside
     * @param string the keyword to be found in tasks
     * @return a taskList that contains tasks with the string found inside
     */
    public TaskList findTasks(String string) {
        TaskList taskList = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(string)) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}
