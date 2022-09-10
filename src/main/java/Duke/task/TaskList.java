package Duke.task;

import Duke.Parser;
import Duke.exceptions.DukeException;
import Duke.exceptions.InvalidIndexException;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * The {@code TaskList} stores relevant information for all tasks in the application.
 * It contains the {@link TaskList#tasks task list}.
 */
public class TaskList {

    private final ArrayList<? super Task> tasks; // Stores the task created by the user.

    /**
     * Constructor for a task list with no task.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for a task list with task loaded from storage.
     */
    public TaskList(Scanner scanner) throws DukeException {
        tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String inputString = scanner.nextLine();
            Parser input = Parser.formatInput(inputString);
            String additionalInputString = scanner.nextLine();
            boolean done = additionalInputString.equals(Task.DONE);
            switch (input.getCommand()) {
            case TODO:
                tasks.add(new TaskTodo(input.getMainData(), done));
                break;
            case DEADLINE:
                tasks.add(new TaskDeadline(input.getMainData(), input.getSecondaryData(), done));
                break;
            case EVENT:
                tasks.add(new TaskEvent(input.getMainData(), input.getSecondaryData(), done));
                break;
            }
        }
    }

    /**
     * Returns an arraylist of all tasks created by the user.
     *
     * @return an arraylist of all tasks created by the user.
     */
    public ArrayList<? super Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a task created by the user given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     * @return the task having the index provided.
     */
    public Task getTask(int index) {
        return (Task) tasks.get(index);
    }

    /**
     * Returns an integer representing the number of tasks in the task list.
     *
     * @return an integer representing the number of tasks in the task list.
     */
    public int getNumberOfTask() {
        return tasks.size();
    }

    public boolean isNotEmpty() {
        return !tasks.isEmpty();
    }

    /**
     * Adds a task into the task list.
     *
     * @param task a task that will be added into the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete a task given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     */
    public Task deleteTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        return (Task) tasks.remove(index - 1);
    }

    /**
     * Check a task as done given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     */
    public Task checkTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) tasks.get(index - 1);
        task.markDone();
        return task;
    }

    /**
     * Uncheck a task as not done given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     */
    public Task uncheckTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) tasks.get(index - 1);
        task.markUndone();
        return task;
    }

    /**
     * Validate if the index is valid.
     *
     * @param index an integer representing the index of task in the task list.
     * @throws InvalidIndexException the error thrown when the index is invalid.
     */
    private void validateIndex(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            String message;
            switch (tasks.size()) {
            case 0:
                message = "Please add a task first!";
                break;
            case 1:
                message = "Please choose the index 1";
                break;
            default:
                message = "Please choose an index between 1 and " + tasks.size();
                break;
            }
            throw new InvalidIndexException(message);
        }
    }
}
