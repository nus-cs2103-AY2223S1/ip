package duke.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * The Parse class, for parsing user commands.
 */
public class Parser {
    /**
     * Static method for parsing the user commands and returning them as a formatted
     * String separated by the | character.
     *
     * @param line The line from the standard input we want to parse.
     * @return Returns the String separated by | for easier parsing
     *         that contains the necessary information for carrying out
     *         further operations in an easily separable form.
     * @throws DukeException Handles duke related exceptions.
     */
    public static String parseCommand(String line) throws DukeException {
        String[] splitString = line.split(" ");
        String command = splitString[0];

        switch (command) {
        case "hello":
            return "hello|";
        case "bye":
            return "bye|";
        case "list":
            return "list|";
        case "mark":
            int taskIdToMark = splitString.length == 2
                    ? Integer.parseInt(splitString[1])
                    : -1;

            return "mark|" + taskIdToMark;
        case "unmark":
            int taskIdToUnmark = splitString.length == 2
                    ? Integer.parseInt(splitString[1])
                    : -1;

            return "unmark|" + taskIdToUnmark;
        case "delete":
            int taskIdToDelete = splitString.length == 2
                    ? Integer.parseInt(splitString[1])
                    : -1;

            return "delete|" + taskIdToDelete;
        case "find":
            String target = splitString.length == 2
                    ? splitString[1]
                    : "";
            return "find|" + target;
        case "todo":
            String[] descTodo = Arrays.copyOfRange(splitString, 1, splitString.length);
            if (descTodo.length == 0) {
                throw new DukeException("The description of a todo should not be empty!");
            }
            line = String.join(" ", descTodo);

            return "todo|" + line;
        case "deadline":
            String[] descDeadline = Arrays.copyOfRange(splitString, 1, splitString.length);
            if (descDeadline.length == 0) {
                throw new DukeException("The description/by time of a deadline should not be empty!");
            }

            line = String.join(" ", descDeadline);
            String descD = line.split("/by")[0].trim();
            String byD = line.split("/by").length == 2
                    ? line.split("/by")[1].trim()
                    : "";
            DateTimeFormatter deadlineFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime byDeadline = LocalDateTime.parse(byD, deadlineFormat);

            return "deadline|" + descD + "|" + byDeadline.format(deadlineFormat);
        case "event":
            String[] descEvent = Arrays.copyOfRange(splitString, 1, splitString.length);
            if (descEvent.length == 0) {
                throw new DukeException("The description/from/end of an event should not be empty!");
            }

            line = String.join(" ", descEvent);
            String descE = line.split("/from")[0].trim();
            String dates = line.split("/from").length == 2
                    ? line.split("/from")[1]
                    : "";

            String startE = "";
            String endE = "";
            if (line.split("/from").length == 2) {
                startE = dates.split("/to")[0].trim();
                endE = dates.split("/to")[1].trim();
            }

            DateTimeFormatter eventFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startEvent = LocalDateTime.parse(startE, eventFormat);
            LocalDateTime endEvent = LocalDateTime.parse(endE, eventFormat);

            return "event|" + descE + "|" + startEvent.format(eventFormat)
                    + "|" + endEvent.format(eventFormat);
        default:
            return "";
        }
    }
}
