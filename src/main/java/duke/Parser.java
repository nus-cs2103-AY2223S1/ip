package duke;

import java.time.format.DateTimeParseException;

import duke.dukeexceptions.DateNotRecognisedException;
import duke.dukeexceptions.EmptyTodoException;
import duke.dukeexceptions.InvalidDeadlineException;
import duke.dukeexceptions.InvalidEventException;
import duke.dukeexceptions.UnknownCommandException;

/**
 * Class which parses commands and executes code based off the commands.
 *
 */
public class Parser {
    /**
     * Fetches and returns all the tasks.
     *
     * @param ui
     * @param taskList
     * @return tasks
     */
    public String executeList(Ui ui, TaskList taskList) {
        return ui.getTasks(taskList);
    }

    /**
     * Marks a task as done
     *
     * @param commandBreakdown
     * @param taskList
     * @param ui
     * @return completed message for marking a task as done
     */
    public String executeMark(String[] commandBreakdown, TaskList taskList, Ui ui) {
        int taskNo = Integer.valueOf(commandBreakdown[1]) - 1;
        Task task = taskList.getTask(taskNo);
        task.markDone();
        assert task.isDone();
        return ui.markTask(task);
    }

    /**
     * Marks a task as undone
     *
     * @param commandBreakdown
     * @param taskList
     * @param ui
     * @return completed message for marking a task as done
     */
    public String executeUnmark(String[] commandBreakdown, TaskList taskList, Ui ui) {
        int taskNo = Integer.valueOf(commandBreakdown[1]) - 1;
        Task task = taskList.getTask(taskNo);
        task.markUndone();
        return ui.unmarkTask(task);
    }

    /**
     * Executes the bye command.
     *
     * @param ui
     * @param storage
     * @param taskList
     * @return message after completing bye command
     */
    public String executeBye(Ui ui, Storage storage, TaskList taskList) {
        String byeMessage = ui.bye();
        storage.write(taskList);
        return byeMessage;
    }

    /**
     * Creates a new ToDo.
     *
     * @param commandBreakdown
     * @param taskList
     * @param ui
     * @return Completed message after creating ToDo
     */
    public String executeTodo(String[] commandBreakdown, TaskList taskList, Ui ui) {
        String todoName = "";
        if (commandBreakdown.length == 1) {
            return new EmptyTodoException().toString();
        }
        for (int i = 1; i < commandBreakdown.length; i++) {
            todoName = todoName + commandBreakdown[i] + " ";
        }
        ToDo todo = new ToDo(todoName);
        taskList.addTask(todo);
        return ui.taskAdded(todo, taskList);
    }

    /**
     * Creates a new deadline.
     *
     * @param fullCommand
     * @param taskList
     * @param ui
     * @return Completed message after creating deadline.
     */
    public String executeDeadline(String fullCommand, TaskList taskList, Ui ui) {
        String[] deadlineSplit = fullCommand.split(" /by ");
        if (deadlineSplit.length != 2) {
            return new InvalidDeadlineException().toString();
        }
        String date = deadlineSplit[1];
        Deadline deadline;
        try {
            deadline = new Deadline(deadlineSplit[0].substring(9, deadlineSplit[0].length()), date);
        } catch (DateTimeParseException e) {
            return new DateNotRecognisedException().toString();
        }
        taskList.addTask(deadline);
        return ui.taskAdded(deadline, taskList);
    }

    /**
     * Creates a new event.
     *
     * @param fullCommand
     * @param taskList
     * @param ui
     * @return Completed message after creating event.
     */
    public String executeEvent(String fullCommand, TaskList taskList, Ui ui) {
        String[] eventSplit = fullCommand.split(" /at ");
        if (eventSplit.length != 2) {
            return new InvalidEventException().toString();
        }
        String date = eventSplit[1];
        Event event;
        try {
            event = new Event(eventSplit[0].substring(6, eventSplit[0].length()), date);
        } catch (DateTimeParseException e) {
            return new DateNotRecognisedException().toString();
        }
        taskList.addTask(event);
        return ui.taskAdded(event, taskList);
    }

    /**
     * Deletes a task
     *
     * @param commandBreakdown
     * @param taskList
     * @param ui
     * @return Completed message after deleting task
     */
    public String executeDelete(String[] commandBreakdown, TaskList taskList, Ui ui) {
        int indToDelete = Integer.valueOf(commandBreakdown[1]) - 1;
        Task toDelete = taskList.getTask(indToDelete);
        taskList.remove(indToDelete);
        return ui.taskDeleted(toDelete, taskList);
    }

    /**
     * Looks for tasks based off keyword.
     *
     * @param commandBreakdown
     * @param taskList
     * @param ui
     * @return tasks that match the keyword
     */
    public String executeFind(String[] commandBreakdown, TaskList taskList, Ui ui) {
        String keyword = "";
        for (int i = 1; i < commandBreakdown.length; i++) {
            keyword += (commandBreakdown[i] + " ");
        }
        keyword = keyword.trim();
        return ui.getMatchingTasks(keyword, taskList);
    }

    /**
     * Parses the command and executes code based off the command.
     *
     * @param fullCommand
     * @param ui
     * @param taskList
     * @param storage
     * @return true if "bye" command was given
     */
    public String parse(String fullCommand, Ui ui, TaskList taskList, Storage storage) {
        String[] commandBreakdown = fullCommand.split(" ");
        String command = commandBreakdown[0];

        switch (command) {
        case "list":
            return executeList(ui, taskList);
        case "mark":
            return executeMark(commandBreakdown, taskList, ui);
        case "unmark":
            return executeUnmark(commandBreakdown, taskList, ui);
        case "bye":
            return executeBye(ui, storage, taskList);
        case "todo":
            return executeTodo(commandBreakdown, taskList, ui);
        case "deadline":
            return executeDeadline(fullCommand, taskList, ui);
        case "event":
            return executeEvent(fullCommand, taskList, ui);
        case "delete":
            return executeDelete(commandBreakdown, taskList, ui);
        case "find":
            return executeFind(commandBreakdown, taskList, ui);
        default:
            return new UnknownCommandException().toString();
        }
    }
}
