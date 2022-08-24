package task;

import exceptions.DukeException;
import exceptions.InvalidIndexException;
import utils.Input;
import utils.Prompt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


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
     * A function that loads a text file into the task list
     */
    public void loadTask(Scanner scanner) throws DukeException {
        while (scanner.hasNext()) {
            String inputString = scanner.nextLine();
            Input input = Input.formatInput(inputString);
            String additionalInputString = scanner.nextLine();
            boolean done = additionalInputString.equals(Task.DONE);
            switch (input.getCommand()) {
            case TODO:
                taskList.add(new TaskTodo(input.getMainData(), done));
                break;
            case DEADLINE:
                taskList.add(new TaskDeadline(input.getMainData(), input.getSecondaryData(), done));
                break;
            case EVENT:
                taskList.add(new TaskEvent(input.getMainData(), input.getSecondaryData(), done));
                break;
            }
        }
        Prompt.fileSuccessfullyLoaded();
        if (taskList.size() > 0) {
            listTask();
            Prompt.lineDivider();
        }
    }

    /**
     * A function that stores a text file into the task list
     */
    public void storeTask(String filepath) {
        try {
            FileWriter fileWriter = new FileWriter(filepath);
            StringBuilder input = new StringBuilder();
            for (Object o : taskList) {
                Task task = (Task) o;
                input.append(task.toStorageString());
            }
            fileWriter.write(input.toString());
            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    /**
     * A function that list all the tasks in the list.
     */
    public void listTask() {
        StringBuilder output = new StringBuilder();
        output.append("\nCurrent Tasking\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            output.append(String.format("%d) %s\n", i + 1, this.taskList.get(i)));
        }
        output.append("Number of tasking: ").append(taskList.size());
        System.out.println(output);
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
