package util;

import alanExceptions.AlanException;
import alanExceptions.InvalidValueException;
import tasks.*;

import java.util.List;

/**
 * This class is used for executing commands.
 */
public class Executor {
    private final Ui ui;
    private final FileFormatter fileFormatter;
    private final Parser parser;
    private Storage alanIO;

    /**
     * Constructor for Executor
     */
    public Executor() {
        this.ui = new Ui();
        this.fileFormatter = new FileFormatter();
        this.parser = new Parser();
        try {
            this.alanIO = new Storage();
        } catch (AlanException e) {
            excException(e.getMessage());
        }
    }

    /**
     * Lists a TaskList
     *
     * @param taskList The task list to be listed.
     */
    public void excList(TaskList taskList) {
        System.out.println(ui.list(taskList.getTaskList()));
    }

    /**
     * Adds an Event to the given task list
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @throws AlanException The exception in case of failure.
     */
    public void excEvent(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.event, userInput);
        Task currentTask = new Event(parsedData);
        taskList.add(currentTask);
        // Append new task to save File
        alanIO.append(fileFormatter.formatTask(currentTask));
        System.out.println(ui.addTask(currentTask, taskList.size()));
    }

    /**
     * Adds a Deadline to the given task list
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @throws AlanException The exception in case of failure.
     */
    public void excDeadline(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.deadline, userInput);
        Task currentTask = new Deadline(parsedData);
        taskList.add(currentTask);
        // Append new task to save File
        alanIO.append(fileFormatter.formatTask(currentTask));
        System.out.println(ui.addTask(currentTask, taskList.size()));
    }

    /**
     * Adds a Todo to the given task list
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @throws AlanException The exception in case of failure.
     */
    public void excTodo(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.todo, userInput);
        Task currentTask = new Todo(parsedData);
        taskList.add(currentTask);
        // Append new task to save File
        alanIO.append(fileFormatter.formatTask(currentTask));
        System.out.println(ui.addTask(currentTask, taskList.size()));
    }

    /**
     * Finds task in task list
     *
     * @param taskList Task list to be searched.
     * @param userInput User input.
     * @throws AlanException Exception in case of failure.
     */
    public void excFind(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.find, userInput);
        List<Task> result = taskList.find(parsedData.getDescription());
        System.out.println(ui.find(taskList, result));
    }

    /**
     * Marks a task on the given list
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @throws AlanException The exception in case of failure.
     */
    public void excMark(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.mark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markDone();
            // Append new task to save File
            alanIO.write(fileFormatter.formatTaskList(taskList.getTaskList()));
            System.out.println(ui.markDone(currentTask));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    /**
     * Unmarks a task on the given list
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @throws AlanException The exception in case of failure.
     */
    public void excUnmark(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.unmark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markUndone();
            // Append new task to save File
            alanIO.write(fileFormatter.formatTaskList(taskList.getTaskList()));
            System.out.println(ui.markUndone(currentTask));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    /**
     * Deletes a specified task
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @throws AlanException The exception in case of failure.
     */
    public void excDelete(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.delete, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            taskList.remove(parsedData.getListIndex());
            // Append new task to save File
            alanIO.write(fileFormatter.formatTaskList(taskList.getTaskList()));
            System.out.println(ui.delete(currentTask, taskList.size()));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    /**
     * Prints the error message
     *
     * @param errorMsg The error message to be printed.
     */
    public void excException(String errorMsg) {
        System.out.println(ui.addSeparator(errorMsg));
    }
}
