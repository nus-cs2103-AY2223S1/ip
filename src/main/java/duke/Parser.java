package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * deals with making sense of the user command
 *
 * @author eugeneleong
 * @version 1.0
 */
public class Parser {

    /**
     * Returns the type of command that the user has inputted
     * @param input by the user
     * @return type of command
     */
    public static String getCommandType(String input) {
        if (input.equals("list")) {
            return "LIST";
        } else if (input.startsWith("mark ")) {
            return "MARK";
        } else if (input.startsWith("unmark ")) {
            return "UNMARK";
        } else if (input.startsWith("todo ")) {
            return "TODO";
        } else if (input.startsWith("deadline ")) {
            return "DEADLINE";
        } else if (input.startsWith("event ")) {
            return "EVENT";
        } else if (input.startsWith("delete ")) {
            return "DELETE";
        } else if (input.startsWith("find ")) {
            return "FIND";
        } else if (input.equals("bye")) {
            return "BYE";
        } else { // to handle unknown inputs, e.g. 'blah', 'todo'
            throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Formats the event date and timing nicely as a LocalDateTime type
     * @param input time in String
     * @return LocalDateTime
     */
    public static LocalDateTime formatEventTime(String input) {
        try {
            String time = input.substring(input.indexOf("/") + 4); // Format: dd/MM/yyyy HHmm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            return LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException dtpe) {
            System.out.println("Please give me a correct time/date! :(");
            return null;
        }
    }
}
