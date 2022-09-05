package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import command.Commands;

/**
 * Utility class containing Task-related methods
 */
public class TaskUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
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
                   + dateTime.format(formatter).replace("T", " ") + ")";
        case EVENT:
            if (tentativeDates.isEmpty()) {
                return "[E]" + getStatusIcon(completionStatus) + " " + description + " (at: "
                       + dateTime.format(formatter).replace("T", " ") + ")";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("[E]").append(getStatusIcon(completionStatus)).append(" ").append(description)
                  .append(" (at: ");
                for (LocalDateTime tentativeDate : tentativeDates) {
                    sb.append(tentativeDate.format(formatter).replace("T", " ")).append(", ");
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
                   + dateTime.format(formatter).replace("T", " ") + ")";
        case EVENT:
            if (tentativeDates.isEmpty()) {
                return "E | " + completionStatus + " | " + description + " | (at: "
                       + dateTime.format(formatter).replace("T", " ") + ")";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("E | ").append(completionStatus).append(" | ").append(description).append(" | (at: ");
                for (LocalDateTime tentativeDate : tentativeDates) {
                    sb.append(tentativeDate.format(formatter).replace("T", " ")).append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append(")");
                return sb.toString();
            }
        default:
            return MALFORMED;
        }
    }
}
