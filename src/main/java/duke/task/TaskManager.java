package duke.task;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Class that manages tasks.
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class TaskManager {

    /**
     * Represents an indentation for replies.
     */
    private static final String INDENTATION = "     ";

    /**
     * Represents a file writer.
     */
    private final FileWriter fw;

    /**
     * Represents an array list of tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for Task Manager.
     * @param fw File writer.
     */
    public TaskManager(FileWriter fw) {
        this.tasks = new ArrayList<>(100);
        this.fw = fw;
    }

    /**
     * Crafts a list of tasks.
     * @return String formatting of tasks in the task list.
     */
    public String craftTaskString() {
        int length = tasks.size();
        String result = "";
        for (int x = 0; x < length; x++) {
            Task task = tasks.get(x);
            if (task == null) {
                break;
            } else {
                if (x == 0) {
                    result += (x + 1) + "." + task;
                } else {
                    result += "\n" + INDENTATION + (x + 1) + "." + task;
                }
            }
        }
        return result;
    }

    /**
     * Finds tasks that contain a certain string.
     * @param s String.
     * @return String formatting of tasks in the task list.
     */
    public String findAndCraftTaskList(String s) {
        List<Task> filteredList = tasks.stream()
                .filter(x -> x.containString(s))
                .collect(Collectors.toList());
        return convertListToFormattedString(filteredList);
    }

    /**
     * Converts a List of Tasks to a formatted String message.
     * @param list List containing Task objects.
     * @return String message.
     */
    private String convertListToFormattedString(List<Task> list) {
        int counter = 1;
        String result = "";
        for (Task task : list) {
            if (counter == 1) {
                result += counter + "." + task;
            } else {
                result += "\n" + INDENTATION + counter + "." + task;
            }
            counter++;
        }
        return result;
    }

    /**
     * Crafts a message to be added into the file.
     * @return String representing the message.
     */
    public String craftTextMessageForFile() {
        int length = tasks.size();
        String result = "";
        for (int x = 0; x < length; x++) {
            Task task = tasks.get(x);
            if (task == null) {
                break;
            } else {
                if (x == 0) {
                    result += task.constructTextFileMessage();
                } else {
                    result += "\n" + task.constructTextFileMessage();
                }
            }
        }
        return result;
    }

    /**
     * Crafts a Reminders List.
     *
     * @return String representing the message.
     */
    public String craftRemindersString() {
        List<Task> filteredList = tasks.stream()
                .filter(x -> x.getTaskType() == Task.TaskType.DEADLINE)
                .filter(x -> !x.checkIfCompleted())
                .collect(Collectors.toList());
        return convertListToFormattedString(filteredList);
    }

    /**
     * Adds task into array of tasks.
     * @param task Given task.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task.
     * @param location Index where the task is located.
     * @return Task.
     */
    public Task removeTask(int location) {
        assert location >= 0 : "the task number should not be negative";
        Task task = tasks.get(location);
        tasks.remove(location);
        return task;
    }

    /**
     * Represents the number of task is the task list.
     * @return Number of tasks in task list.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Represents the number of task is the task list that corresponds to the given Task Type.
     * @param type Task type.
     * @return Number of tasks.
     */
    public int getNumberOfMatchingTasks(Task.TaskType type) {
        return (int) tasks.stream()
                .filter(x -> x.getTaskType() == type)
                .count();
    }

    /**
     * Marks a task as completed.
     * @param location Index where the task is located.
     * @return Task.
     */
    public Task markTaskAsCompleted(int location) {
        assert location >= 0 : "the task number should not be negative";
        Task task = tasks.get(location);
        task.markComplete();
        return task;
    }

    /**
     * Marks a task as incomplete.
     * @param location Index where the task is located.
     * @return Task.
     */
    public Task markTaskAsIncomplete(int location) {
        assert location >= 0 : "the task number should not be negative";
        Task task = tasks.get(location);
        task.markIncomplete();
        return task;
    }
}
