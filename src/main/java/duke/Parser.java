package duke;

import java.time.LocalDateTime;

/**
 * Class containing parsing methods, or conversions.
 */
public class Parser {
    static final String TASK_TODO = " T ";
    static final String TASK_EVENT = " E ";
    static final String TASK_DEADLINE = " D ";


    /**
     * Parses date and time string and converts it into a LocalDateTime.
     * @param dateTime string with date and time in format (dd/mm/yyyy hhmm).
     * @return LocalDateTime object containing date and time.
     */
    public static LocalDateTime parseDate(String dateTime) {
        String[] splitDateTime = dateTime.trim().split(" ");
        String[] dateArray = splitDateTime[0].split("/");
        String time = splitDateTime[1];

        return LocalDateTime.of(
                Integer.parseInt(dateArray[2]),
                Integer.parseInt(dateArray[1]),
                Integer.parseInt(dateArray[0]),
                Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4))
        );
    }

    /**
     * Parses string containing task into a Task object.
     * @param task string containing task.
     * @return Task object.
     */
    public static Task fileStringToTask(String task) {
        String[] taskSplit = task.split("\\|");
        String type = taskSplit[0];
        switch (type) {
        case TASK_TODO:
            return new Todo(taskSplit[2].trim(), taskSplit[1].trim().equals("1"));
        case TASK_EVENT:
            return new Event(taskSplit[2].trim(), taskSplit[3], taskSplit[1].trim().equals("1"));
        case TASK_DEADLINE:
            return new Deadline(taskSplit[2].trim(), taskSplit[3], taskSplit[1].trim().equals("1"));
        default:
            return null;
        }
    }

    public static Event parseEventInput(String eventCommand) {
        int slashPos = eventCommand.indexOf("/at");
        String taskName = eventCommand.substring(5, slashPos - 1) + " ";
        String deadline = eventCommand.substring(slashPos + 3);
        return new Event(taskName, deadline);
    }

    public static Deadline parseDeadlineInput(String deadlineCommand) {
        int slashPos = deadlineCommand.indexOf("/by");
        String taskName = deadlineCommand.substring(8, slashPos - 1) + " ";
        String deadline = deadlineCommand.substring(slashPos + 3);
        return new Deadline(taskName, deadline);
    }

    public static Todo parseTodoInput(String todoCommand){
        String item = todoCommand.substring(4);
        return new Todo(item);
    }
}
