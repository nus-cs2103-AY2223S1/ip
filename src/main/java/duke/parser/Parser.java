package duke.parser;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.TasksList;

public class Parser {
    public static Command parse(String command, Storage storage, TasksList tasksList, Ui ui) throws DukeException {
        String[] words = command.split(" ", 2);
        if (ExitCommand.isCommand(command)) {
            return new ExitCommand(ui, storage, tasksList);
        } else if (ListCommand.isCommand(command)) {
            return new ListCommand(tasksList);
        } else if (MarkCommand.isCommand(words[0])) {
            return new MarkCommand(tasksList, storage, words);
        } else if (UnmarkCommand.isCommand(words[0])) {
            return new UnmarkCommand(tasksList, storage, words);
        } else if (AddCommand.isCommand(words[0])) {
            return new AddCommand(tasksList, storage, words);
        } else if (DeleteCommand.isCommand(words[0])) {
            return new DeleteCommand(tasksList, storage, words);
        } else if (FindCommand.isCommand(words[0])) {
            return new FindCommand(tasksList, words);
        } else {
            return new InvalidCommand();
        }
    }
}
