package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InvalidInput;
import duke.exception.UnknownCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private enum Commands {list, mark, unmark, todo, event, deadline, delete, bye, find}

    public Command parse(String input) throws DukeException {
        Commands command;
        try {
            command = Commands.valueOf(input.split(" ")[0]);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommand();
        }
        switch (command) {
            case list:
                return new ListCommand();
            case mark:
                String indexToMarkString = input.substring(4).trim();
                return new MarkCommand(indexToMarkString, true);
            case unmark:
                String indexToUnmarkString = input.substring(6).trim();
                return new MarkCommand(indexToUnmarkString, false);
            case todo:
                String todoDescription = input.substring(4).trim();
                if (todoDescription.length() == 0)
                    throw new InvalidInput("The description of a todo cannot be empty.");
                return new AddCommand("T", todoDescription, null);
            case event:
                String[] eventDetails = input.substring(5).split("/at");
                if (eventDetails.length != 2) throw new InvalidInput("Ensure input format is correct.");
                String eventDescription = eventDetails[0].strip();
                if (eventDescription.length() == 0)
                    throw new InvalidInput("The description of event cannot be empty.");
                try {
                    LocalDate date = LocalDate.parse(eventDetails[1].strip());
                    return new AddCommand("E", eventDescription, date);
                } catch (DateTimeParseException e) {
                    throw new InvalidInput("Date format should be yyyy-mm-dd");
                }
            case deadline:
                String[] deadlineDetails = input.substring(8).split("/by");
                if (deadlineDetails.length != 2) throw new InvalidInput("Ensure input format is correct.");
                String deadlineDescription = deadlineDetails[0].strip();
                if (deadlineDescription.length() == 0)
                    throw new InvalidInput("The description of deadline cannot be empty.");
                try {
                    LocalDate date = LocalDate.parse(deadlineDetails[1].strip());
                    return new AddCommand("D", deadlineDescription, date);
                } catch (DateTimeParseException e) {
                    throw new InvalidInput("Date format should be yyyy-mm-dd");
                }
            case delete:
                String indexString = input.substring(6).trim();
                return new DeleteCommand(indexString);
            case bye:
                return new ExitCommand();
            case find:
                String query = input.substring(4).trim();
                if (query.equals("")) {
                    throw new InvalidInput("Query cannot be empty");
                }
                return new FindCommand(query);
            default:
                throw new UnknownCommand();
        }
    }
}
