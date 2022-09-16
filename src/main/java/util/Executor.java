package util;

import monkeexceptions.MonkeException;
import monkeexceptions.InvalidValueException;
import keyword.Keywords;
import tasks.*;

import java.util.List;

import static util.InputType.help;

/**
 * This class is used for executing commands.
 */
public class Executor {
    private final Ui ui;
    private final FileFormatter fileFormatter;
    private final Parser parser;
    private Storage storage;

    /**
     * Constructor for Executor.
     */
    public Executor() {
        this.ui = new Ui();
        this.fileFormatter = new FileFormatter();
        this.parser = new Parser();
        try {
            this.storage = Storage.getInstance();
        } catch (MonkeException e) {
            excException(e.getMessage());
        }
    }

    /**
     * Lists a TaskList.
     *
     * @param taskList The task list to be listed.
     */

    /**
     * Lists a TaskList.
     *
     * @param taskList The task list to be listed.
     * @return The reply string.
     */
    public String excList(TaskList taskList) {
        return ui.list(taskList.getTaskList());
    }

    /**
     * Adds an Event to the given task list.
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @return The reply string.
     * @throws MonkeException The exception in case of failure.
     */
    public String excEvent(TaskList taskList, String userInput) throws MonkeException {
        ParsedData parsedData = parser.parse(InputType.event, userInput);
        Task currentTask = new Event(parsedData);
        taskList.add(currentTask);
        // At this point taskList should have at least one element
        assert taskList.size() > 0 : "TaskList is still empty after attempt to add task";
        // Append new task to save File
        storage.append(fileFormatter.formatTask(currentTask));

        return ui.addTask(currentTask, taskList.size());
    }

    /**
     * Adds a Deadline to the given task list.
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @return The reply string.
     * @throws MonkeException The exception in case of failure.
     */
    public String excDeadline(TaskList taskList, String userInput) throws MonkeException {
        ParsedData parsedData = parser.parse(InputType.deadline, userInput);
        Task currentTask = new Deadline(parsedData);
        taskList.add(currentTask);
        // At this point taskList should have at least one element
        assert taskList.size() > 0 : "TaskList is still empty after attempt to add task";
        // Append new task to save File
        storage.append(fileFormatter.formatTask(currentTask));

        return ui.addTask(currentTask, taskList.size());
    }

    /**
     * Adds a Todo to the given task list.
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @return The reply string.
     * @throws MonkeException The exception in case of failure.
     */
    public String excTodo(TaskList taskList, String userInput) throws MonkeException {
        ParsedData parsedData = parser.parse(InputType.todo, userInput);
        Task currentTask = new Todo(parsedData);
        taskList.add(currentTask);
        // At this point taskList should have at least one element
        assert taskList.size() > 0 : "TaskList is still empty after attempt to add task";
        // Append new task to save File
        storage.append(fileFormatter.formatTask(currentTask));

        return ui.addTask(currentTask, taskList.size());
    }

    /**
     * Finds task in task list.
     *
     * @param taskList Task list to be searched.
     * @param userInput User input.
     * @return The reply string.
     * @throws MonkeException Exception in case of failure.
     */
    public String excFind(TaskList taskList, String userInput) throws MonkeException {
        ParsedData parsedData = parser.parse(InputType.find, userInput);
        List<Task> result = taskList.find(parsedData.getDescription());

        return ui.find(taskList, result);
    }

    /**
     * Marks a task on the given list.
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @return The reply string.
     * @throws MonkeException The exception in case of failure.
     */
    public String excMark(TaskList taskList, String userInput) throws MonkeException {
        ParsedData parsedData = parser.parse(InputType.mark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markDone();
            // Append new task to save File
            storage.write(fileFormatter.formatTaskList(taskList.getTaskList()));

            return ui.markDone(currentTask);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    /**
     * Unmarks a task on the given list.
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @return The reply string.
     * @throws MonkeException The exception in case of failure.
     */
    public String excUnmark(TaskList taskList, String userInput) throws MonkeException {
        ParsedData parsedData = parser.parse(InputType.unmark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markUndone();
            // Append new task to save File
            storage.write(fileFormatter.formatTaskList(taskList.getTaskList()));

            return ui.markUndone(currentTask);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    /**
     * Deletes a specified task.
     *
     * @param taskList The task list.
     * @param userInput The user input.
     * @return The reply string.
     * @throws MonkeException The exception in case of failure.
     */
    public String excDelete(TaskList taskList, String userInput) throws MonkeException {
        ParsedData parsedData = parser.parse(InputType.delete, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            taskList.remove(parsedData.getListIndex());
            // Append new task to save File
            storage.write(fileFormatter.formatTaskList(taskList.getTaskList()));

            return ui.delete(currentTask, taskList.size());
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    /**
     * Assigns a custom keyword.
     *
     * @param userInput The user input.
     * @return The reply string.
     * @throws MonkeException The exception in case of failure.
     */
    public String excAkw(String userInput) throws MonkeException {
        ParsedData parsedData = parser.parse(InputType.akw, userInput);
        keyword.Keywords.getInstance().assign(parsedData.getKw(), parsedData.getCommandkw());

        return ui.akw(parsedData.getKw(), parsedData.getCommandkw());
    }

    /**
     * Removes a custom keyword.
     *
     * @param userInput The user input.
     * @return The reply string.
     * @throws MonkeException The exception in case of failure.
     */
    public String excRkw(String userInput) throws MonkeException {
        ParsedData parsedData = parser.parse(InputType.rkw, userInput);
        keyword.Keywords.getInstance().remove(parsedData.getDescription());

        return ui.rkw(parsedData.getDescription());
    }

    /**
     * Produces a help message.
     *
     * @param userInput The user input.
     * @return The reply string.
     * @throws MonkeException The exception in case of failure.
     */
    public String excHelp(String userInput) throws MonkeException {
        ParsedData parsedData;
        try {
            parsedData = parser.parse(help, userInput);
        } catch (MonkeException e) {
            return "For more specific instructions, type \"help\" followed by a command.\n" +
                    "Here are the available commands:\n" + Keywords.getInstance().getDefaultKeywords();
        }

        return Keywords.getInstance().getHelpMsg(parsedData.getDescription());

    }

    /**
     * Exits the program.
     *
     * @return Bye greeting, but its deprecated.
     */
    public String excBye() {
        System.exit(0);
        return "Goodbye! See you soon!";
    }

    /**
     * Prints the error message
     *
     * @param errorMsg The error message to be printed.
     * @return The exception message.
     */
    public String excException(String errorMsg) {
        return ui.addSeparator(errorMsg);
    }
}
