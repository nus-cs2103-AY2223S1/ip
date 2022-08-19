package task;

import exceptions.InvalidIndexException;
import utils.Prompt;

import java.util.ArrayList;


/**
 * The {@code TaskList} stores relevant information for all tasks in the application.
 * It contains the {@link TaskList#taskList task list}.
 */
public class TaskList {

    private final ArrayList<? super Task> taskList;

    /**
     * Constructor for a task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }


    /**
     * A function that list all the tasks in the list.
     */
    public void listTask() {
        System.out.println("\nCurrent Tasking");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ") " + taskList.get(i - 1));
        }
        System.out.println("Number of tasking: " + taskList.size());
    }


    public void addTask(Task task) {
        taskList.add(task);
        Prompt.addTask(task);
    }

    /**
     * Given an index, delete a task.
     *
     * @param index index of the task we would like to delete.
     */
    public void deleteTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) taskList.remove(index - 1);
        Prompt.deleteTask(task);
    }

    /**
     * Given an index, mark a task as done.
     *
     * @param index index of the task we would like to mark as done.
     */
    public void checkTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) taskList.get(index - 1);
        task.markDone();
    }

    /**
     * Given an index, mark a task as undone.
     *
     * @param index index of the task we would like to mark as undone.
     */
    public void uncheckTask(int index) throws InvalidIndexException {
        validateIndex(index - 1);
        Task task = (Task) taskList.get(index - 1);
        task.markUndone();
    }

    /**
     * Given an index, check if the index is valid.
     *
     * @param index index of the task we would like to check.
     * @throws InvalidIndexException error thrown when the index is invalid.
     */
    private void validateIndex(int index) throws InvalidIndexException {
        if (index < 0 || index >= taskList.size()) {
            String message;
            switch (taskList.size()) {
                case 0:
                    message = "Please add a task first!";
                    break;
                case 1:
                    message = "Please choose the index 1";
                    break;
                default:
                    message = "Please choose an index between 1 and " + taskList.size();
                    break;
            }
            throw new InvalidIndexException(message);
        }
    }
}
