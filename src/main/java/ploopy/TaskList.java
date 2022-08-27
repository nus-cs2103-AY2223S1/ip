package ploopy;

import java.util.ArrayList;

/**
 * Stores the list of tasks and operates on them based on
 * user input
 *
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private UI ui;
    private Storage storage;

    /** Constructor that takes a UI and Storage objects
     *
     * @param ui UI to display message.
     * @param storage Storage to store tasks.
     */
    public TaskList(UI ui, Storage storage) {
        taskList = new ArrayList<>();
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Prints the list of tasks in the order they were added.
     *
     */
    public void displayList() {
        ui.showTopWindow();
        int index = 1;
        for (Task item : taskList) {
            System.out.println("\n" + index + "." + item);
            index++;
        }
        ui.showBottomWindow();
    }

    /**
     * Marks the provided task as done, prints the acknowledgement
     * and rewrites all tasks to storage file.
     *
     * @param taskIndex Index of task to be marked done.
     * @throws PloopyException If a storage file error occurs.
     */
    public void markTask(int taskIndex) throws PloopyException {
        Task current = taskList.get(taskIndex - 1);
        current.markDone();
        ui.markTaskMessage(current);
        storage.rewriteFile(taskList);
    }

    /**
     * Marks the provided task as undone, prints the acknowledgement
     * and rewrites all tasks to storage file.
     * @param taskIndex Index of task to be marked undone.
     * @throws PloopyException If a storage file error occurs.
     */
    public void unmarkTask(int taskIndex) throws PloopyException {
        Task current = taskList.get(taskIndex - 1);
        current.unmark();
        ui.unmarkTaskMessage(current);
        storage.rewriteFile(taskList);
    }

    /**
     * Deletes the provided task, prints the acknowledgement
     * and rewrites all tasks to storage file.
     *
     *
     * @param taskNumber Index of task to be marked undone.
     * @throws PloopyException If a storage file error occurs.
     */

    public void deleteTask(int taskNumber) throws PloopyException {
        ui.deleteTaskMessage(taskList.get(taskNumber - 1), taskList.size() - 1);
        taskList.remove(taskNumber - 1);
        storage.rewriteFile(taskList);
    }

    /**
     * Creates a ToDo task and adds it to the taskList.
     * Prints acknowledgement and writes task to storage file.
     *
     * @param input Name of task.
     * @throws PloopyException If a storage file error occurs.
     */
    public void createToDo(String input) throws PloopyException {
        Task newTask = new ToDo(input);
        taskList.add(newTask);
        ui.addTaskMessage(newTask, taskList.size());
        storage.writeToFile(newTask);
    }

    /**
     * Creates a Deadline task and adds it to the taskList.
     * Prints acknowledgement and writes task to storage file.
     *
     * @param name Name of task.
     * @param date Date of task.
     * @throws PloopyException If a storage file error occurs.
     */
    public void createDeadline(String name, String date) throws PloopyException {
        Task newTask = new Deadline(name, date);
        taskList.add(newTask);
        ui.addTaskMessage(newTask, taskList.size());
        storage.writeToFile(newTask);
    }

    /**
     * Creates an Event task and adds it to the taskList.
     * Prints acknowledgement and writes task to storage file.
     *
     * @param name Name of task
     * @param date Date of task
     * @throws PloopyException If a storage file error occurs.
     */
    public void createEvent(String name, String date) throws PloopyException {
        Task newTask = new Event(name, date);
        taskList.add(newTask);
        ui.addTaskMessage(newTask, taskList.size());
        storage.writeToFile(newTask);
    }

    /**
     * Add tasks to taskList based on data from storage file.
     *
     * @param input Data from storage file.
     */
    public void addTasksFromFile(String input) {
        String[] inputSequence = input.split("_");
        String type = inputSequence[0];
        String name = inputSequence[2];
        String date = inputSequence.length > 3 ? inputSequence[3] : "";
        Task createdTask = Task.addTaskFromFile(type, name, date);
        if (inputSequence[1].equals("1")) {
            createdTask.markDone();
        }
        taskList.add(createdTask);
    }
}
