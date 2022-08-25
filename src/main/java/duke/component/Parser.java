package duke.component;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EndCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;


public class Parser {

    public Command parse(String message, TaskList tasks) throws DukeException {
        String[] splitMessage = message.split("\\s+", 2);
        CommandType commandType = CommandType.parse(splitMessage[0]);
        switch (commandType) {
        case BYE:
            return new EndCommand();
        case LIST:
            return new ListCommand(tasks);
        case MARK:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to tell me the task number!");
            }
            return new MarkCommand(splitMessage[1], tasks);
        case UNMARK:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to tell me the task number!");
            }
            return new UnmarkCommand(splitMessage[1], tasks);
        case TODO:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to add the description!");
            }
            return new ToDoCommand(splitMessage[1], tasks);
        case DEADLINE:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to add the description!");
            }
            return new DeadlineCommand(splitMessage[1], tasks);
        case EVENT:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to add the description!");
            }
            return new EventCommand(splitMessage[1], tasks);
        case DELETE:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to tell me the task number!");
            }
            return new DeleteCommand(splitMessage[1], tasks);
        default:
            throw new DukeException("Sorry, there is no such command!");
        }
    }
}
