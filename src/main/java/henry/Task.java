package henry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import command.Commands;
import exceptions.HenryException;

/**
 * The base for tasks that can be added to the
 * task list. All task types (Todo, Deadline, Event)
 * are just instances of Task with different types passed
 * into the constructor.
 */
public class Task {

    private static final String MALFORMED = "[T][ ] MALFORMED TASK";

    protected String description;
    protected boolean isDone;
    private final Commands type;
    private LocalDateTime date;
    private final List<LocalDateTime> tentativeDates;

    public Task(Commands type, String description, LocalDateTime date) {
        this(type, description, date, false, new ArrayList<>());
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
    public Task(Commands type, String description, LocalDateTime date, boolean isDone,
                List<LocalDateTime> tentativeDates) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
        this.tentativeDates = tentativeDates;
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

    private static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    private static List<LocalDateTime> parseMultipleDateTimes(String input) {
        List<LocalDateTime> dates = new ArrayList<>();
        String[] tokens = input.split(",");
        for (String token : tokens) {
            dates.add(parseDateTime(token.trim()));
        }
        return dates;
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

    /**
     * Gets the description of the task.
     *
     * @return a String representing the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the LocalDateTime object related to the task.
     *
     * @return a LocalDateTime object representing the date of the task
     */
    public LocalDateTime getLocalDateTime() {
        return date;
    }

    /**
     * Gets the type of the task.
     *
     * @return a Commands with the enum value of the task type
     */
    public Commands getType() {
        return type;
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
        case EVENT:
            if (tentativeDates.isEmpty()) {
                return "[E]" + getStatusIcon() + " " + description + " (at: "
                       + date.format(formatter).replace("T", " ") + ")";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("[E]").append(getStatusIcon()).append(" ").append(description).append(" (at: ");
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
        case EVENT:
            if (tentativeDates.isEmpty()) {
                return "E | " + (isDone ? 1 : 0) + " | " + description + " | (at: "
                       + date.format(formatter).replace("T", " ") + ")";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("E | ").append(isDone ? 1 : 0).append(" | ").append(description).append(" | (at: ");
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
     * Adds a tentative date to the Task. Only applicable for Event tasks.
     * @param date the tentative date to be added
     */
    public void addTentativeDate(LocalDateTime date) {
        tentativeDates.add(date);
    }

    /**
     * Confirms the tentative date of the task. Only applicable for Event tasks.
     * @param index the index of the date chosen
     */
    public void confirmDate(int index) {
        date = tentativeDates.get(index);
        tentativeDates.clear();
    }
}
