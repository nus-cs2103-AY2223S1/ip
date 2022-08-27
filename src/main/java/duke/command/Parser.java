package duke.command;

import duke.utilities.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser to make sense of user input.
 */
public class Parser {
    /**
     * Parses user input to a standard format
     * @param input The user input
     * @return The standardised input.
     * @throws DukeException Handles duke related exceptions.
     */
    public static String parseCommand(String input) throws DukeException {

        String[] segments = input.split(" ");

        if (input.equals("list")) {
            return "list|";
        } else if (segments[0].equals("find")) {
            input = input.replace("find", "");
            if (input.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return "find|" + input.trim();
        } else if (segments[0].equals("mark")) {
            int taskId;
            if (segments[1].equals("")) {
                taskId = -1;
            } else {
                taskId = Integer.parseInt(segments[1]);
            }
            return "mark|" + taskId;
        } else if (segments[0].equals("unmark")) {
            int taskId;
            if (segments[1].equals("")) {
                taskId = -1;
            } else {
                taskId = Integer.parseInt(segments[1]);
            }
            return "unmark|" + taskId;
        } else if (segments[0].equals("todo")) {
            input = input.replace("todo", "");
            if (input.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return "todo|" + input.trim();
        } else if (segments[0].equals("deadline")) {
            input = input.replace("deadline", "");
            if (input.equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] parts = input.split("/by");
            String desc = parts[0].trim();
            String by = parts[1].trim();
            DateTimeFormatter deadlineFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime byDeadline = LocalDateTime.parse(by, deadlineFormat);
            return "deadline|" + desc + "|" + byDeadline.format(deadlineFormat);
        }  else if (segments[0].equals("event")) {
            input = input.replace("event", "");
            if (input.equals("")) {
                throw new DukeException("The description of a event cannot be empty.");
            }
            String[] duration = input.split("/from");
            String descE = duration[0].trim();
            String[] time = duration[1].split("/to");
            String start = time[0].trim();
            String end = time[1].trim();
            DateTimeFormatter eventFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startE = LocalDateTime.parse(start, eventFormat);
            LocalDateTime endE = LocalDateTime.parse(end, eventFormat);
            return "event|" + descE + "|" + startE.format(eventFormat) +
                    "|" + endE.format(eventFormat);
        } else if (segments[0].equals("delete")) {
            int taskId;
            if (segments[1].equals("")) {
                taskId = -1;
            } else {
                int index = Integer.parseInt(segments[1]);
                taskId = index - 1;
            }
            return "delete|" + taskId;
        } else {
            return "";
        }
    }
}
