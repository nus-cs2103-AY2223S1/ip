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
     * @param userInput The line of command that user input.
     * @return The respective duke.command.Command.
     * @throws DukeException If invalid command encountered.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        String[] input = userInput.split(" ", 2);
        switch (input[0]) {
            case ("bye"):
                return new ExitCommand();
            case ("list"):
                if (input.length == 1) {
                    return new ListCommand();
                }
            case ("todo"):
                return new AddCommand(new Todo(input[1]));
            case ("event"): {
                String[]eventString = input[1].split("/at ", 2);
                return new AddCommand(new Event(eventString[0], LocalDate.parse(eventString[1])));
            }
            case ("deadline"): {
                String[] deadlineString = input[1].split("/by ", 2);
                return new AddCommand(new Deadline(deadlineString[0], LocalDate.parse(deadlineString[1])));
            }
            case ("mark"):
                return new MarkCommand(Integer.parseInt(input[1]));
            case ("unmark"):
                return new UnmarkCommand(Integer.parseInt(input[1]));
            case ("delete"):
                return new DeleteCommand(Integer.parseInt(input[1]));
            case("find"):
                return new FindCommand(input[1].split(" "));
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
