package dukebot;

import java.util.ArrayList;

/**
 * Encapsulates the list containing tasks as a class.
 */
public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param newTask Task to be added to the list.
     */
    public void addTask(Task newTask) {
        this.add(newTask);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param taskToDelete Task to be deleted from the list.
     * @return Task that was deleted from the list
     */
    public Task deleteTask(int taskToDelete) {
        Task deletedTask = this.remove(taskToDelete);
        Task.reduceTaskCount();
        return deletedTask;
    }

    /**
     * Converts TaskList to ArrayList of Strings.
     *
     * @return ArrayList of Strings containing the Tasks in custom String format.
     */
    public ArrayList<String> convertTasksToList() {
        ArrayList<String> commandToWrite = new ArrayList<>();
        for (Task task : this) {
            String command = task.getTaskType() + ",";
            if (task.getStatusIcon().equals("X")) {
                command += "1,";
            } else {
                command += "0,";
            }
            command += task.getDescription();
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                command = command + "," + task.getTime();
            }
            command += "," + task.getPriority().name().charAt(0);
            commandToWrite.add(command);
        }
        return commandToWrite;
    }
}
