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

    private final ArrayList<? super Task> tasks;

    /**
     * Constructor for a task list with no task.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a task list with task loaded from storage.
     */
    public TaskList(Scanner scanner) throws DukeException {
        this.tasks = new ArrayList<>();
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

    public ArrayList<? super Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return (Task) tasks.get(index);
    }

    public int getNumberOfTask() {
        return tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Given an index, delete a task.
     *
     * @param index index of the task we would like to delete.
     */
    public Task deleteTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        return (Task) tasks.remove(index - 1);
    }

    /**
     * Given an index, mark a task as done.
     *
     * @param index index of the task we would like to mark as done.
     */
    public Task checkTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) this.tasks.get(index - 1);
        task.markDone();
        return task;
    }

    /**
     * Given an index, mark a task as undone.
     *
     * @param index index of the task we would like to mark as undone.
     */
    public Task uncheckTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) this.tasks.get(index - 1);
        task.markUndone();
        return task;
    }

    /**
     * Given an index, check if the index is valid.
     *
     * @param index index of the task we would like to check.
     * @throws InvalidIndexException error thrown when the index is invalid.
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
