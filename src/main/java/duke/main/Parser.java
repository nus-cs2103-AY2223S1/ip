package duke.main;

import duke.DukeException;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Make sense of user command input and return the respective command
     *
     * @param userInput The line of command that user input.
     * @return The respective Command
     * @throws DukeException If invalid command encountered.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        String[] input = userInput.split(" ", 2);
        Command command;
        switch (input[0]) {
        case ("bye"):
            if (input.length != 1) {
                throw new DukeException("I'm sorry, but you cannot add more commands behind 'bye' :-(");
            }
            command = new ExitCommand();
            break;
        case ("list"):
            if (input.length != 1) {
                throw new DukeException("I'm sorry, but you cannot add more commands behind 'list' :-(");
            }
            command = new ListCommand();
            break;
        case ("todo"):
            command = new AddCommand(new Todo(input[1]));
            break;
        case ("event"): {
            String[] eventString = input[1].split("/at ", 2);
            command = new AddCommand(new Event(eventString[0], LocalDate.parse(eventString[1])));
            break;
        }
        case ("deadline"): {
            String[] deadlineString = input[1].split("/by ", 2);
            command = new AddCommand(new Deadline(deadlineString[0], LocalDate.parse(deadlineString[1])));
            break;
        }
        case ("mark"):
            command = new MarkCommand(Integer.parseInt(input[1]));
            break;
        case ("unmark"):
            command = new UnmarkCommand(Integer.parseInt(input[1]));
            break;
        case ("delete"):
            command = new DeleteCommand(Integer.parseInt(input[1]));
            break;
        case ("find"):
            command = new FindCommand(input[1].split(" "));
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        assert command != null : "There should be a new command created";
        return command;
    }
}
