package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<String> taskStringList) {
        tasks = new ArrayList<Task>();
        for (int i = 0; i < taskStringList.size(); i++) {
            String taskString = taskStringList.get(i);
            Task task = parseTaskString(taskString);
            tasks.add(task);
        }
    }

    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    public Task deleteTask(int index) {
        Task removedTask = tasks.remove(index);
        return removedTask;
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns TaskList containing all found tasks.
     * @param keyword Keyword to be searched for.
     * @return TaskList containing all tasks that matched keyword.
     */
    public TaskList find(String keyword) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    @Override
    public String toString() {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += "\n" + (i + 1) + ". " + tasks.get(i).toString();
        }
        return tasksString;
    }

    private Task parseTaskString(String taskString) {
        String withoutNumber = taskString.substring(taskString.indexOf(".") + 2);
        String typeOfTask = withoutNumber.substring(1, 2);
        String marked = withoutNumber.substring(4, 5);
        String description = withoutNumber.substring(7);
        if (typeOfTask.equals("T")) {
            Task task = new Todo(description);
            if (marked.equals("X")) {
                task.markAsDone();
            }
            return task;
        } else if (typeOfTask.equals("D")) {
            String[] descriptionAndDate = description.split(" \\(by: ");
            String descriptionOnly = descriptionAndDate[0];
            String dateOnly = descriptionAndDate[1].substring(0, descriptionAndDate[1].length() - 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateOnly, formatter);

            Task task = new Deadline(descriptionOnly, dateTime);
            if (marked.equals("X")) {
                task.markAsDone();
            }
            return task;
        } else {
            String[] descriptionAndDate = description.split(" \\(at: ");
            String descriptionOnly = descriptionAndDate[0];
            String dateOnly = descriptionAndDate[1].substring(0, descriptionAndDate[1].length() - 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateOnly, formatter);

            Task task = new Event(descriptionOnly, dateTime);
            if (marked.equals("X")) {
                task.markAsDone();
            }
            return task;
        }
    }

}
