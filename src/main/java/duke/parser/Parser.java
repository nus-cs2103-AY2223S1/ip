package duke.parser;

import java.io.IOException;

import duke.command.*;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDescriptionException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the location where the commands entered into the program are
 * processed and how should the <code>Ui</code>, or Seaward, should respond.
 */
public class Parser {

    private static TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises a <code>Parser</code> object which takes in tasks, storage and ui
     * to make sense of the user commands.
     * @param tasks <code>TaskList</code> object representing the lists of tasks.
     * @param storage <code>Storage</code> object which deals with reading and writing
     *                to a file that is located on the user's hard disk.
     * @param ui <code>Ui</code> object that deals with the interactions with the user.
     */
    public Parser(TaskList tasks, Storage storage, Ui ui) {
        taskList = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Returns the appropriate response to a command given by the user. If the command is invalid,
     * then an error message is returned.
     * @param s Command given by the user.
     * @return A response depending on the command given by the user.
     * @throws InvalidCommandException If the command is not one of the cases, or if the index does not
     *     exist in the <code>TaskList</code> object.
     * @throws InvalidDescriptionException If the command does not have a valid description.
     * @throws IOException If the <code>Storage</code> object cannot read or write to the file.
     */
    public String readInputString(String s) throws InvalidCommandException,
            InvalidDescriptionException, IOException {
        String[] splitCommand = s.split(" ", 2);
        assert splitCommand[0] != null : "Invalid command";
        String command = splitCommand[0];
        switch (command) {
        case "bye": {
            return new ByeCommand().read(taskList, ui, storage);
        }
        case "list": {
            return new ListCommand().read(taskList, ui, storage);
        }
        case "mark": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                throw new InvalidCommandException("Task does not exist.");
            }
            return new MarkCommand(index).read(taskList, ui, storage);
        }
        case "unmark": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                throw new InvalidCommandException("Task does not exist.");
            }
            return new UnmarkCommand(index).read(taskList, ui, storage);
        }
        case "delete": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            int numOfTasks = taskList.getNumOfTasks();
            if (index + 1 > numOfTasks) {
                throw new InvalidCommandException("Task does not exist.");
            }
            return new DeleteCommand(index).read(taskList, ui, storage);
        }
        case "todo": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description.");
            }
            return new TodoCommand(splitCommand[1]).read(taskList, ui, storage);
        }
        case "deadline": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description and deadline.");
            }
            return new DeadlineCommand(splitCommand[1]).read(taskList, ui, storage);
        }
        case "event": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description and time/date.");
            }
            return new EventCommand(splitCommand[1]).read(taskList, ui, storage);
        }
        case "find": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            return new FindCommand(splitCommand[1]).read(taskList, ui, storage);
        }
        case "findtag": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            return new FindTagCommand(splitCommand[1]).read(taskList, ui, storage);
        }
        case "cdf": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                throw new InvalidCommandException("Task does not exist.");
            }
            return new ChangeDateFormatCommand(index).read(taskList, ui, storage);
        }
        default:
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
