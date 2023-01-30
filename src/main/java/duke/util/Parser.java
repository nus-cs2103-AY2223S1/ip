package duke.util;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.ChangeStatusCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

/**
 * Parser to make sense of user input.
 */
public class Parser {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a Parser object.
     *
     * @param storage  Storage class to be used
     * @param ui       Ui class to be used
     * @param taskList TaskList to be used
     */
    public Parser(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Enum representing the different types of commands.
     */
    public static enum CommandName {
        bye, list, mark, unmark, delete, todo, deadline, event, find, help
    }

    /**
     * Parses the user input and returns a Command object.
     *
     * @param input User input
     * @return Command object after parsing input
     * @throws DukeException if user input is invalid
     */
    public Command parse(String input) throws DukeException {
        String[] tokens = input.split(" ");
        if (tokens.length == 0) {
            throw new DukeException(
                    "I'm sorry but I don't know what that means. Enter `help` to view list of available commands.");
        }
        int taskIndex;
        switch (CommandName.valueOf(tokens[0])) {
        case mark:
            return new ChangeStatusCommand(storage, ui, taskList, input, true);
        case unmark:
            return new ChangeStatusCommand(storage, ui, taskList, input, false);
        case list:
            return new ListCommand(storage, ui, taskList);
        case delete:
            assert input.split(" ").length > 1;
            taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(storage, ui, taskList, taskIndex);
        case bye:
            return new ExitCommand(storage, ui, taskList);
        case find:
            return new FindCommand(storage, ui, taskList, input);
        case help:
            return new HelpCommand(storage, ui, taskList);
        default:
            return new AddCommand(storage, ui, taskList, input);
        }

    }

}
