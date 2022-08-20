package duke;

import duke.command.*;

public class Parser {
    public static Command inputCommand(String command, TaskList tasks, Ui ui) throws DukeException {
        String[] returnedArray = command.split(" ");
        if (returnedArray.length == 0 || returnedArray[0] == null || returnedArray[0].equals("")) {
            throw new DukeException("Sorry, I am a bit hard of hearing.\nCould you please repeat yourself?" +
                    "\nIf unsure, please use duke.command [help] for the list of commands that I understand.");
        } else {
            Command.Commands word = Command.checkEnums(returnedArray[0]);
            switch (word) {
            case bye:
                return new ExitCommand();
            case list:
                return new ListCommand(tasks, ui);
            case help:
                return new HelpCommand();
            case mark:
                return new MarkCommand(returnedArray, tasks, ui);
            case unmark:
                return new UnmarkCommand(returnedArray, tasks, ui);
            case delete:
                return new DeleteCommand(returnedArray, tasks, ui);
            case todo:
                return new ToDoCommand(command, tasks, ui);
            case deadline:
                return new DeadlineCommand(command, tasks, ui);
            case event:
                return new EventCommand(command, tasks, ui);
            case invalid: //Notice the control flow still reaches here even if [invalid] is input
                throw new DukeException("I don't understand your duke.command.\nCould you please repeat yourself?" +
                        "\nIf unsure, please use duke.command [help] for the list of commands that I understand.");
            default:
                throw new DukeException("please do not mess with my software. This message should never" +
                        "pop up.");
            }
        }
    }
}
