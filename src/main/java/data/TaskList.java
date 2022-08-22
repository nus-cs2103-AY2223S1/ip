package data;

import data.exception.DukeException;
import tasks.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * To list down all the tasks that are added to the list.
     * @return A list of all the tasks added.
     */
    public String list() {
        int len = tasks.size();
        StringBuilder stringBuilder = new StringBuilder("\tHere are the tasks in your list :D");
        for (int i = 0; i < len; i++) {
            int index = i + 1;
            String task = "\n\t" + index + ". " + tasks.get(i);
            stringBuilder.append(task);
        }
        return stringBuilder.toString();
    }

    public String getTasks(String date) {
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StringBuilder stringBuilder = new StringBuilder("\tYour tasks for today include:");
        int count = 1;
        for (Task task : this.tasks) {
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                if (task.getDate().equals(parsedDate)) {
                    String formatted = String.format("\n\t%d. %s", count, task);
                    stringBuilder.append(formatted);
                    count++;
                }
            }
        }

        if (count == 1) {
             return String.format("\tNo tasks on %s", parsedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        } else {
            return stringBuilder.toString();
        }
    }

    public void addToList(Task task) {
        this.tasks.add(task);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int i) throws DukeException {
        if (i > this.tasks.size() || i < 0) {
            throw new DukeException("No such task exists!");
        }
        return this.tasks.get(i);
    }

    public Task deleteTask(int i) throws DukeException {
        if (i > this.tasks.size() || i < 0) {
            throw new DukeException("No such task exist!");
        }
        Task task = this.tasks.get(i - 1);
        this.tasks.remove(i - 1);
        return task;
    }
}
