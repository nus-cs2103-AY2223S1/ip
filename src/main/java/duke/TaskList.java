package duke;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int i) throws DukeException {
        try {
            return tasks.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number!");
        }
    }

    public int size() {
        return tasks.size();
    }

    public static String convertTasksToListString(ArrayList<Task> tasks) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String newLine = (i + 1) + ". " + tasks.get(i).toString() + "\n";
            list.append(newLine);
        }
        return list.toString();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I can't find such a task to delete!");
        }
    }

    public TaskList getTasksOnDate(String dateStr) throws DukeException {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isOnThisDate(dateStr)) {
                tasksOnDate.add(t);
            }
        }
        return new TaskList(tasksOnDate);
    }

    public TaskList getTasksWithWord(String input) throws DukeException {
        ArrayList<Task> tasksWithWord = new ArrayList<>();
        for (Task t : tasks) {
            if (t.doesDescriptionContain(input)) {
                tasksWithWord.add(t);
            }
        }
        return new TaskList(tasksWithWord);
    }

    @Override
    public String toString() {
        return convertTasksToListString(tasks);
    }
}
