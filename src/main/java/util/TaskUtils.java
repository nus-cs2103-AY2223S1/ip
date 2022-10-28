package util;

import static util.DateUtils.parseDateTime;
import static util.DateUtils.parseMultipleDateTimes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import command.Commands;
import exceptions.HenryException;
import henry.Task;

/**
 * Utility class containing Task-related methods
 */
public class TaskUtils {

    private static final String MALFORMED = "[T][ ] MALFORMED TASK";

    private static String getStatusIcon(boolean isDone) {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Creates a String representation of a Task with the input parameters.
     *
     * @param type             the type of Task
     * @param completionStatus the completion status of the Task
     * @param description      the description of the Task
     * @param dateTime         the LocalDateTime associated with the Task
     * @param tentativeDates   the list of tentative dates associated with the Task, can be empty
     * @return a String representation of the Task with the given input parameters
     */
    public static String toStandardString(Commands type, boolean completionStatus, String description,
                                          LocalDateTime dateTime, List<LocalDateTime> tentativeDates) {
        description = description.trim();
        switch (type) {
        case TODO:
            return "[T]" + getStatusIcon(completionStatus) + " " + description;
        case DEADLINE:
            return "[D]" + getStatusIcon(completionStatus) + " " + description + " (by: "
                   + DateUtils.dateToString(dateTime) + ")";
        case EVENT:
            if (tentativeDates.isEmpty()) {
                return "[E]" + getStatusIcon(completionStatus) + " " + description + " (at: "
                       + DateUtils.dateToString(dateTime) + ")";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("[E]").append(getStatusIcon(completionStatus)).append(" ").append(description)
                  .append(" (at: ");
                for (LocalDateTime tentativeDate : tentativeDates) {
                    sb.append(DateUtils.dateToString(tentativeDate)).append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append(")");
                return sb.toString();
            }
        default:
            return MALFORMED;
        }
    }

    /**
     * Creates a String representation of a Task with the input parameters,
     * as it would be read from or written to the storage file.
     *
     * @param type             the type of Task
     * @param completionStatus the completion status of the Task
     * @param description      the description of the Task
     * @param dateTime         the LocalDateTime associated with the Task
     * @param tentativeDates   the list of tentative dates associated with the Task, can be empty
     * @return a String representation of the Task with the given input parameters
     */
    public static String getFileEncodedTask(Commands type, int completionStatus, String description,
                                            LocalDateTime dateTime,
                                            List<LocalDateTime> tentativeDates) {
        description = description.trim();
        switch (type) {
        case TODO:
            return "T | " + completionStatus + " | " + description;
        case DEADLINE:
            return "D | " + completionStatus + " | " + description + " | (by: "
                   + DateUtils.dateToString(dateTime) + ")";
        case EVENT:
            if (tentativeDates.isEmpty()) {
                return "E | " + completionStatus + " | " + description + " | (at: "
                       + DateUtils.dateToString(dateTime) + ")";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("E | ").append(completionStatus).append(" | ").append(description).append(" | (at: ");
                for (LocalDateTime tentativeDate : tentativeDates) {
                    sb.append(DateUtils.dateToString(tentativeDate)).append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append(")");
                return sb.toString();
            }
        default:
            return MALFORMED;
        }
    }

    /**
     * Parses a new Task object from the given String input
     *
     * @param input the String to be converted into a Task
     * @return a new Task representing the input
     * @throws HenryException if the input is malformed
     */
    public static Task parseTask(String input) {
        String[] tokens = input.split("\\|");
        Commands type;
        String description = tokens[2].trim();
        LocalDateTime date;
        boolean isComplete = tokens[1].trim().equals("1");
        List<LocalDateTime> tentativeDates = new ArrayList<>();

        String prefix;
        String cleaned;
        prefix = tokens[0].trim();

        switch (prefix) {
        case "T":
            type = Commands.TODO;
            date = LocalDateTime.MAX;
            break;
        case "D":
            type = Commands.DEADLINE;
            cleaned = tokens[3].replace("(by:", "").replace(")", "").trim();
            date = parseDateTime(cleaned);
            break;
        case "E":
            type = Commands.EVENT;
            cleaned = tokens[3].replace("(at:", "").replace(")", "").trim();
            tentativeDates = parseMultipleDateTimes(cleaned);
            date = tentativeDates.get(0);
            break;
        default:
            throw new HenryException("INPUT TASK IS MALFORMED!");
        }
        return new Task(type, description, date, isComplete, tentativeDates);
    }
}
