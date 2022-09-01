package henry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.Commands;

/**
 * The base for tasks that can be added to the
 * task list. All task types (Todo, Deadline, Event)
 * are just instances of Task with different types passed
 * into the constructor.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    private final Commands type;
    private final LocalDateTime date;

    public Task(Commands type, String description, LocalDateTime date) {
        this(type, description, date, false);
    }

    /**
     * Constructor for a task. The task will have the specified
     * type, description, date, and status (if used).
     *
     * @param type        the type of task. Can be Todo, Deadline, or Event.
     * @param description the description of the task.
     * @param date        the date that the task must be completed by/is due.
     *                    Only used for Deadline and Event tasks.
     * @param isDone      whether the task is completed.
     */
    public Task(Commands type, String description, LocalDateTime date, boolean isDone) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
    }

    public static Task parseTask(String input) {
        String[] tokens = input.split("\\|");
        Commands type;
        LocalDateTime date = null;

        String prefix;
        String cleaned;
        prefix = tokens[0].trim();
        boolean isComplete = tokens[1].trim().equals("1");
        String description = tokens[2].trim();
        switch (prefix) {
        case "T":
            type = Commands.TODO;
            break;
        case "D":
            type = Commands.DEADLINE;
            cleaned = tokens[3].replace("(by:", "").replace(")", "").trim();
            date = parseDateTime(cleaned);
            break;
        default:
            type = Commands.EVENT;
            cleaned = tokens[3].replace("(at:", "").replace(")", "").trim();
            date = parseDateTime(cleaned);
            break;
        }
        return new Task(type, description, date, isComplete);
    }

    private static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    private String getStatusIcon() {
        return isDone ? "[X]" : "[ ]"; // mark done task with X
    }

    /**
     * Sets the status of this task as the input boolean.
     *
     * @param status the desired status of the task
     */
    public void setComplete(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        switch (type) {
        case TODO:
            return "[T]" + getStatusIcon() + " " + description;
        case DEADLINE:
            return "[D]" + getStatusIcon() + " " + description + " (by: "
                   + date.format(formatter).replace("T", " ") + ")";
        default:
            return "[E]" + getStatusIcon() + " " + description + " (at: "
                   + date.format(formatter).replace("T", " ") + ")";
        }
    }

    /**
     * Returns the file-formatted version of the task. This is different from
     * the toString() method, in that it is simpler and thus easier to parse.
     *
     * @return a string representing the task as it would be written to the file
     */
    public String toSimpleString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        description = description.trim();
        switch (type) {
        case TODO:
            return "T | " + (isDone ? 1 : 0) + " | " + description;
        case DEADLINE:
            return "D | " + (isDone ? 1 : 0) + " | " + description + " | (by: "
                   + date.format(formatter).replace("T", " ") + ")";
        default:
            return "E | " + (isDone ? 1 : 0) + " | " + description + " | (at: "
                   + date.format(formatter).replace("T", " ") + ")";
        }
    }
}
