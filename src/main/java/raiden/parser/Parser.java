package raiden.parser;

import raiden.RaidenException;
import raiden.Storage;
import raiden.Ui;
import raiden.command.AddCommand;
import raiden.command.Command;
import raiden.command.DeleteCommand;
import raiden.command.EditCommand;
import raiden.command.ExitCommand;
import raiden.command.FindCommand;
import raiden.command.HelpCommand;
import raiden.command.InvalidCommand;
import raiden.command.ListCommand;
import raiden.command.MarkCommand;
import raiden.command.UnmarkCommand;
import raiden.task.TaskList;

/**
 * Represents a parser that interprets the user's command and returns the respective Command object.
 */
public class Parser {
    /**
     * Returns the respective Command for the given string of command, Storage, TaskList and Ui.
     *
     * @param command The command to be interpreted.
     * @param storage The Storage associated with the command, if any.
     * @param taskList The TaskList associated with the command, if any.
     * @param ui The Ui associated with the command, if any.
     * @return The respective Command that should follow from the user's input.
     * @throws RaidenException if error occurs in the Command's execution.
     */
    public static Command parse(String command, Storage storage, TaskList taskList, Ui ui) throws RaidenException {
        String[] words = command.split(" ", 2);
        if (ExitCommand.isCommand(command)) {
            return new ExitCommand(ui, storage, taskList);
        } else if (ListCommand.isCommand(command)) {
            return new ListCommand(taskList);
        } else if (MarkCommand.isCommand(words[0])) {
            return new MarkCommand(taskList, storage, words);
        } else if (UnmarkCommand.isCommand(words[0])) {
            return new UnmarkCommand(taskList, storage, words);
        } else if (AddCommand.isCommand(words[0])) {
            return new AddCommand(taskList, storage, words);
        } else if (DeleteCommand.isCommand(words[0])) {
            return new DeleteCommand(taskList, storage, words);
        } else if (FindCommand.isCommand(words[0])) {
            return new FindCommand(taskList, words);
        } else if (HelpCommand.isCommand(command)) {
            return new HelpCommand();
        } else if (EditCommand.isCommand(words[0])) {
            return new EditCommand(taskList, storage, words);
        }
        return new InvalidCommand();
    }
}
