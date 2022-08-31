package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import duke.commands.*;
import duke.tasks.*;

/**
 * The Parser class encapsulates the parsing of user input and file text when loading from the hard disk.
 */
public class Parser {
    /**
     * Parses the given input to determine the type of command the input contains.
     * @param input Input to be parsed.
     * @return Command object
     * @throws DukeException
     */
    public static Command parseInput(String input) throws DukeException{
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0].trim();
        String detail = "";
        Boolean description_needed = command.equals("todo") || command.equals("deadline") || command.equals("event") ||
                command.equals("mark") || command.equals("unmark") || command.equals("delete") || command.equals("find");
        if (splitInput.length <= 1 && description_needed) {
            throw new DukeException("Description of command is required.");
        } else if (description_needed){
            detail = splitInput[1].trim();
        }

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(detail);
        case "unmark":
            return new UnmarkCommand(detail);
        case "todo":
            return new TodoCommand(detail);
        case "deadline":
            return new DeadlineCommand(detail);
        case "event":
            return new EventCommand(detail);
        case "delete":
            return new DeleteCommand(detail);
        case "find":
            return new FindCommand(detail);
        default:
            throw new DukeException(command + " is an invalid command.");
        }
    }

    /**
     * Parses text from file when tasks are loaded from the hard disk.
     * @param line String to be parsed.
     * @return Task parsed from input string.
     */
    public static Task parseFromFile(String line) {
        String[] line_split = line.split("]", 3);
        String type = line_split[0];
        String status = line_split[1];
        String rest = line_split[2];
        if (type.equals("[T")) {
            Todo todo = new Todo(rest.trim());
            if (status.equals("[X")) {
                todo.markDone();
            }
            return todo;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a 'on' dd/MM/yyyy");
            if (type.equals("[D")) {
                String[] rest_split = rest.split("by:", 2);
                String description = rest_split[0].replaceAll(".$", "").trim();
                String time = rest_split[1].replaceAll(".$", "").trim();
                LocalDateTime deadlineDateTime = LocalDateTime.parse(time, formatter);
                Deadline deadline = new Deadline(description, deadlineDateTime);
                if (status.equals("[X")) {
                    deadline.markDone();
                }
                return deadline;
            } else {
                String[] rest_split = rest.split("at:", 2);
                String description = rest_split[0].replaceAll(".$", "").trim();
                String time = rest_split[1].replaceAll(".$", "").trim();
                LocalDateTime eventDateTime = LocalDateTime.parse(time, formatter);
                Event event = new Event(description, eventDateTime);
                if (status.equals("[X")) {
                    event.markDone();
                }
                return event;
            }
        }
    }
}
