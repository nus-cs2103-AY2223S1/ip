package duke;

import duke.command.*;

/**
 * Contains a single static method to read user inputs
 * @author Nephelite
 * @version
 */
public class Parser {
    /**
     * Static method to read a String command and return the corresponding Command object
     * @param command in String form
     * @param tasks the TaskList Duke is using
     * @param ui the Ui Duke is using
     * @return a Command that corresponds to the input String command
     * @throws DukeException
     * @since 0.1
     */
    public static Command inputCommand(String command, TaskList tasks, Ui ui) throws DukeException {
        String[] returnedArray = command.split(" ");
        if (returnedArray.length == 0 || returnedArray[0] == null || returnedArray[0].equals("")) {
            throw new DukeException("Sorry, I am a bit hard of hearing.\nCould you please repeat yourself?"
                    + "\nIf unsure, please use duke.command [help] for the list of commands that I understand.");
        } else {
            Command.RecognisedCommand word = Command.checkEnums(returnedArray[0]);
            switch (word) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand(tasks, ui);
            case HELP:
                return new HelpCommand();
            case MARK:
                return new MarkCommand(returnedArray, tasks, ui);
            case UNMARK:
                return new UnmarkCommand(returnedArray, tasks, ui);
            case DELETE:
                return new DeleteCommand(returnedArray, tasks, ui);
            case TODO:
                return new ToDoCommand(command, tasks, ui);
            case DEADLINE:
                return new DeadlineCommand(command, tasks, ui);
            case EVENT:
                return new EventCommand(command, tasks, ui);
            case find:
                if (returnedArray.length > 2) {
                    throw new DukeException("this <find> command is invalid.\n"
                            + " Please use command [help] for documentation on proper use.");
                }
                return new FindCommand(returnedArray[1], tasks, ui);
            case invalid: //Notice the control flow still reaches here even if [invalid] is input
                throw new DukeException("I don't understand your duke.command.\nCould you please repeat yourself?"
                        + "\nIf unsure, please use command [help] for the list of commands that I understand.");
            default:
                throw new DukeException("please do not mess with my software. This message should never"
                        + " pop up.");
            }
        }
    }
}
