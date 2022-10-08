package parser;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteCommand;
import command.EmptyCommand;
import command.ExitCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.ViewScheduleCommand;
import task.TaskList;
import ui.Ui;

/**
 * <h1>Parser class</h1>
 * Parses the user's input and generates the appropriate command.
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Parser object.
     *
     * @param tasks list of Tasks the user currently has.
     * @param ui the User Interface that prints out the output.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns the appropriate Command after parsing the input String.
     *
     * @param input String to be parsed.
     * @return Command to be executed.
     */
    public Command parse(String input) {
        String commandString = getCommandKeywordFromInput(input);
        switch (commandString) {
        case "":
            return new EmptyCommand(tasks, ui);
        case "bye":
            return new ExitCommand(tasks, ui);
        case "list":
            return new ListCommand(tasks, input, ui);
        case "todo":
            return new AddTodoCommand(tasks, input, ui);
        case "deadline":
            return new AddDeadlineCommand(tasks, input, ui);
        case "event":
            return new AddEventCommand(tasks, input, ui);
        case "mark":
            return new MarkCommand(tasks, input, ui);
        case "unmark":
            return new UnmarkCommand(tasks, input, ui);
        case "delete":
            return new DeleteCommand(tasks, input, ui);
        case "find":
            return new FindCommand(tasks, input, ui);
        case "schedule":
            return new ViewScheduleCommand(tasks, input, ui);
        default:
            return new InvalidCommand(tasks, ui);
        }
    }

    private String getCommandKeywordFromInput(String str) {
        String[] splitString = str.split(" ", 2);
        switch (splitString[0]) {
        case "bye":
            if (splitString.length > 1) {
                return "invalid";
            }
            return "bye";
        case "list":
            if (splitString.length > 1) {
                return "invalid";
            }
            return "list";
        default:
            return splitString[0];
        }
    }
}
