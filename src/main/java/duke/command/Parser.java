package duke.command;

import duke.utilities.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@@author DanielLimWeiEn -reused
/**
 * Parser to make sense of user input.
 */
public class Parser {
    /**
     * Parses user input to a standard format
     * @param input The user input.
     * @return The standardised input.
     * @throws DukeException Handles duke related exceptions.
     */
    public static String parseCommand(String input) throws DukeException {

        String[] segments = input.split(" ");

        if (input.equals("list")) {
            return "list|";
        } else if (input.equals("bye")) {
            return "bye|";
        } else if (segments[0].equals("remind")) {
            return "remind|";
        }else if (segments[0].equals("find")) {
            return findCommand(input);
        } else if (segments[0].equals("mark")) {
            return markCommand(segments);
        } else if (segments[0].equals("unmark")) {
            return unmarkCommand(segments);
        } else if (segments[0].equals("todo")) {
            return todoCommand(input);
        } else if (segments[0].equals("deadline")) {
            return deadlineCommand(input);
        }  else if (segments[0].equals("event")) {
            return eventCommand(input);
        } else if (segments[0].equals("delete")) {
            return deleteCommand(segments);
        } else {
            return "";
        }
    }

    /**
     * Parses user find command to a standard format
     * @param input The user input.
     * @return The standardised input.
     * @throws DukeException Handles duke related exceptions.
     */
    public static String findCommand(String input) throws DukeException {
        input = input.replace("find", "");
        if (input.equals("")) {
            throw new DukeException("The description of a find cannot be empty.");
        }
        return "find|" + input.trim();
    }

    /**
     * Parses user mark command to a standard format
     * @param segments The user input.
     * @return The standardised input.
     */
    public static String markCommand(String[] segments) {
        int taskId;
        if (segments[1].equals("")) {
            taskId = -1;
        } else {
            taskId = Integer.parseInt(segments[1]);
        }
        return "mark|" + taskId;
    }

    /**
     * Parses user unmark command to a standard format
     * @param segments The user input.
     * @return The standardised input.
     */
    public static String unmarkCommand(String[] segments) {
        int taskId;
        if (segments[1].equals("")) {
            taskId = -1;
        } else {
            taskId = Integer.parseInt(segments[1]);
        }
        return "unmark|" + taskId;
    }

    /**
     * Parses user todo command to a standard format
     * @param input The user input.
     * @return The standardised input.
     * @throws DukeException Handles duke related exceptions.
     */
    public static String todoCommand(String input) throws DukeException {
        input = input.replace("todo", "");
        if (input.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return "todo|" + input.trim();
    }

    /**
     * Parses user deadline command to a standard format
     * @param input The user input.
     * @return The standardised input.
     * @throws DukeException Handles duke related exceptions.
     */
    public static String deadlineCommand(String input) throws DukeException {
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
    }

    /**
     * Parses user eventCommand command to a standard format
     * @param input The user input.
     * @return The standardised input.
     * @throws DukeException Handles duke related exceptions.
     */
    public static String eventCommand(String input) throws DukeException {
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
    }

    /**
     * Parses user eventCommand command to a standard format
     * @param segments The user input.
     * @return The standardised input.
     */

    public static String deleteCommand(String[] segments) {
        int taskId;
        if (segments[1].equals("")) {
            taskId = -1;
        } else {
            taskId = Integer.parseInt(segments[1]);
        }
        return "delete|" + taskId;
    }

}
//@@author DanielLimWeiEn
