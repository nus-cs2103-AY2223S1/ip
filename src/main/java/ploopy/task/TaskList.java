package ploopy.task;

import ploopy.PloopyException;
import ploopy.Storage;
import ploopy.ui.TextUI;

import java.util.ArrayList;

/**
 * Stores the list of tasks and operates on them based on
 * user input
 *
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;

    /**
     * Constructor that takes a UI and Storage objects
     *
     * @param storage Storage to store tasks.
     */
    public TaskList(Storage storage) {
        taskList = new ArrayList<>();
        this.storage = storage;
    }


    /**
     * Prints the list of tasks in the order they were added.
     *
     */
    public String displayList() {
        String list = "";
        int index = 1;
        for (Task item : taskList) {
            list+= index + "." + item + "\n";
            index++;
        }
        return list;
    }

    /**
     * Marks the provided task as done, prints the acknowledgement
     * and rewrites all tasks to storage file.
     *
     * @param taskIndex Index of task to be marked done.
     * @throws PloopyException If a storage file error occurs.
     */
    public String markTask(int taskIndex) throws PloopyException {
        Task current = taskList.get(taskIndex - 1);
        current.markDone();
        storage.rewriteFile(taskList);
        return TextUI.markTaskMessage(current);
    }

    /**
     * Marks the provided task as undone, prints the acknowledgement
     * and rewrites all tasks to storage file.
     * @param taskIndex Index of task to be marked undone.
     * @throws PloopyException If a storage file error occurs.
     */
    public String unmarkTask(int taskIndex) throws PloopyException {
        Task current = taskList.get(taskIndex - 1);
        current.unmark();
        storage.rewriteFile(taskList);
        return TextUI.unmarkTaskMessage(current);
    }

    /**
     * Deletes the provided task, prints the acknowledgement
     * and rewrites all tasks to storage file.
     *
     *
     * @param taskNumber Index of task to be marked undone.
     * @throws PloopyException If a storage file error occurs.
     */

    public String deleteTask(int taskNumber) throws PloopyException {
        Task deletedTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        storage.rewriteFile(taskList);
        return TextUI.deleteTaskMessage(deletedTask, taskList.size());
    }

    public String createTask(String type, String name, String date) throws PloopyException {
        Task newTask = Task.of(type, name, date);
        taskList.add(newTask);
        storage.writeToFile(newTask);
        return TextUI.addTaskMessage(newTask, taskList.size());
    }

    /**
     * Iterates through all tasks in list and adds
     * tasks matching keyword to a second list.
     * Displays that list to user.
     *
     * @param keyword Keyword for tasks to match.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        String list = "";
        for (Task task : taskList) {
            if (task.getName().contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        if (matchedTasks.size() > 0) {
            list+= TextUI.foundTasks();
            int index = 1;
            for (Task matched : matchedTasks) {
                list+= "\n" + index + "." + matched;
                index++;
            }
            return list;
        } else {
            return TextUI.noTasksFound();
        }

    }

    /**
     * Add tasks to taskList based on data from storage file.
     *
     * @param data Data from storage file.
     */
    public void addTasksFromFile(String data) {
        String[] inputSequence = data.split("_");
        String type = inputSequence[0];
        String name = inputSequence[2];
        String date = type.equals("T") ? "" : inputSequence[3];
        String priority = type.equals("T") ? inputSequence[3] : inputSequence[4];
        Task createdTask = Task.of(type, name, date);
        if (inputSequence[1].equals("1")) {
            createdTask.markDone();
        }
        if (Integer.parseInt(priority) == 1) {
            createdTask.setPriority("high");
        }
        taskList.add(createdTask);
    }

    public String setTaskPriority(int taskNumber, String priority) throws PloopyException {
        Task task = taskList.get(taskNumber - 1);
        task.setPriority(priority);
        storage.rewriteFile(taskList);
        return TextUI.changeTaskPriority(task);
    }
}
