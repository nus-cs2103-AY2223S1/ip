package parser;

import java.util.Scanner;

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
import task.TaskList;
import ui.Ui;

/**
 * <h1>Parser class</h1>
 * Parses the user's input and generates the appropriate command.
 */
public class Parser {
    private Scanner sc;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Parser object.
     *
     * @param tasks list of Tasks the user currently has.
     * @param ui the User Interface that prints out the output.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.sc = new Scanner(System.in);
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
        if (input.equals("")) {
            return new EmptyCommand(tasks, ui);
        } else if (input.equals("bye")) {
            return new ExitCommand(tasks, ui);
        } else if (input.equals("list")) {
            return new ListCommand(tasks, input, ui);
        } else if (input.startsWith("todo")) {
            return new AddTodoCommand(tasks, input, ui);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(tasks, input, ui);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(tasks, input, ui);
        } else if (input.startsWith(("mark"))) {
            return new MarkCommand(tasks, input, ui);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(tasks, input, ui);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(tasks, input, ui);
        } else if (input.startsWith("find")) {
            return new FindCommand(tasks, input, ui);
        } else {
            return new InvalidCommand(tasks, ui);
        }
    }
}
