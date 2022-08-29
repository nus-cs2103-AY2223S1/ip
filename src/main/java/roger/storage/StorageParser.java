package roger.storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import roger.tasks.Deadline;
import roger.tasks.Event;
import roger.tasks.Task;
import roger.tasks.ToDo;


/**
 * Encapsulates the storage format for tasks, and conversion between the
 * `taskString` storage format and Task instances.
 * We define two valid taskString formats:
 * 1. "E | 1 | task name | 2022-10-02"
 * 2. "E | 1 | task name"
 */
public class StorageParser {
    private static final String TASK_TYPE_EVENT = "E";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_TODO = "T";

    private static final String TASK_INDICATOR_DONE = "1";
    private static final String TASK_INDICATOR_NOT_DONE = "0";

    /**
     * Parse the taskString.
     *
     * @param taskString String in the taskString format.
     * @return The task corresponding to the taskString.
     */
    public static Task toTask(String taskString) {
        String[] args = taskString.split(" \\| ");
        String taskType = args[0];

        try {
            switch (taskType) {
            case TASK_TYPE_EVENT:
                return StorageParser.toEvent(args);
            case TASK_TYPE_DEADLINE:
                return StorageParser.toDeadline(args);
            case TASK_TYPE_TODO:
                return StorageParser.toToDo(args);
            default:
                throw new IllegalArgumentException(
                        "taskString does not have a valid task type ('E', 'D', 'T').");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to parse taskString");
        }
    }

    private static Event toEvent(String[] args) {
        boolean isDone = args[1].equals(TASK_INDICATOR_DONE);
        String name = args[2];
        LocalDate date = LocalDate.parse(args[3]);

        Event event = new Event(name, date);
        if (isDone) {
            event.markAsDone();
        }

        return event;
    }

    private static Deadline toDeadline(String[] args) {
        boolean isDone = args[1].equals(TASK_INDICATOR_DONE);
        String name = args[2];
        LocalDate date = LocalDate.parse(args[3]);

        Deadline deadline = new Deadline(name, date);
        if (isDone) {
            deadline.markAsDone();
        }

        return deadline;
    }

    private static ToDo toToDo(String[] args) {
        boolean isDone = args[1].equals(TASK_INDICATOR_DONE);
        String name = args[2];

        ToDo todo = new ToDo(name);
        if (isDone) {
            todo.markAsDone();
        }

        return todo;
    }

    /**
     * Parse the Task.
     *
     * @param task The task to be parsed.
     * @return The taskString corresponding to the Task.
     */
    public static String toTaskString(Task task) {
        if (task instanceof Event) {
            Event event = (Event) task;
            return StorageParser.toTaskString(event);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return StorageParser.toTaskString(deadline);
        } else if (task instanceof ToDo) {
            ToDo todo = (ToDo) task;
            return StorageParser.toTaskString(todo);
        } else {
            throw new IllegalArgumentException(
                    "Task does not belong to either Event, Deadline, or ToDo task.");
        }
    }

    private static String toTaskString(Event event) {
        return String.format(
                "%s | %s | %s | %s",
                TASK_TYPE_EVENT,
                event.isDone() ? TASK_INDICATOR_DONE : TASK_INDICATOR_NOT_DONE,
                event.getName(),
                event.getDate().toString()
        );
    }

    private static String toTaskString(Deadline deadline) {
        return String.format(
                "%s | %s | %s | %s",
                TASK_TYPE_DEADLINE,
                deadline.isDone() ? TASK_INDICATOR_DONE : TASK_INDICATOR_NOT_DONE,
                deadline.getName(),
                deadline.getDate().toString()
        );
    }

    private static String toTaskString(ToDo todo) {
        return String.format(
                "%s | %s | %s",
                TASK_TYPE_TODO,
                todo.isDone() ? TASK_INDICATOR_DONE : TASK_INDICATOR_NOT_DONE,
                todo.getName()
        );
    }
}
