package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> data;

    public TaskList() {
        this.data = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return data.get(index);
    }

    public int getTotalTasks() {
        return data.size();
    }

    public Task getLatestTask() {
        return data.get(data.size() - 1);
    }

    public void deleteTask(int index) {
        data.remove(index);
    }

    public void addTodo(String taskDescription) {
        data.add(new Todo(taskDescription));
    }

    public void addDeadLine(String taskDescription, String by) {
        data.add(new Deadline(taskDescription, by));
    }

    public void addEvent(String taskDescription, String at) {
        data.add(new Event(taskDescription, at));
    }

    public void listTasks() {
        for (int i = 0; i < data.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + data.get(i));
        }
    }

    public void listTasks(LocalDate date) {
        for (Task task : data) {
            if (!task.getTaskType().equals("T")) {
                if (task.getTaskType().equals("E")) {
                    if (LocalDate.parse(((Event)task).getAt().split(" ")[0]).equals(date)) {
                        System.out.println(task);
                    }
                } else if (task.getTaskType().equals("D")) {
                    if (LocalDate.parse(((Deadline) task).getBy().split(" ")[0]).equals(date)) {
                        System.out.println(task);
                    }
                }
            }
        }
    }

    public void markItem(int index) {
        data.get(index).markAsDone();
    }

    public void unMarkItem(int index) {
        data.get(index).markAsNotDone();
    }

    /**
     * Finds and prints tasks which contain the specified keyword.
     *
     * @param keyword keyword to search for.
     */
    public void find(String keyword) {
        for (Task task : data) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(task);
            }
        }
    }
}
