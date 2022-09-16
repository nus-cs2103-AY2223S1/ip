package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.TaskNotFoundException;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

public class Command {
    protected TaskStorage storage;
    protected TaskList taskList;
    protected Ui ui;

    public Command(TaskStorage storage, TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    public String execute(ArrayList<String> parsedInput) throws DukeException {
        try {
            switch (parsedInput.get(0)) {
                case "bye":
                    ExitCommand exitCommand = new ExitCommand(storage, taskList, ui);
                    return exitCommand.sayGoodbye();
                case "list":
                    ListCommand listCommand = new ListCommand(storage, taskList, ui);
                    return listCommand.listTasks();
                case "todo":
                    TodoCommand todoCommand = new TodoCommand(storage, taskList, ui);
                    return todoCommand.addTodo(parsedInput);
                case "deadline":
                    DeadlineCommand deadlineCommand = new DeadlineCommand(storage, taskList, ui);
                    return deadlineCommand.addDeadline(parsedInput);
                case "event":
                    EventCommand eventCommand = new EventCommand(storage, taskList, ui);
                    return eventCommand.addEvent(parsedInput);
                case "mark":
                    MarkCommand markCommand = new MarkCommand(storage, taskList, ui);
                    return markCommand.mark(parsedInput);
                case "unmark":
                    UnmarkCommand unmarkCommand = new UnmarkCommand(storage, taskList, ui);
                    return unmarkCommand.unmark(parsedInput);
                case "find":
                    FindCommand findCommand = new FindCommand(storage, taskList, ui);
                    return findCommand.findTask(parsedInput);
                case "delete":
                    DeleteCommand deleteCommand = new DeleteCommand(storage, taskList, ui);
                    return deleteCommand.deleteTask(parsedInput);
                case "help":
                    HelpCommand helpCommand = new HelpCommand(storage, taskList, ui);
                    return helpCommand.help();
                default:
                    throw new DukeException("Invalid input");
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (TaskNotFoundException e) {
            return e.getMessage();
        }
    }
}
