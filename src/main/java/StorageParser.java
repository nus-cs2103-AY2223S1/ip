import java.time.LocalDate;


/**
 * Encapsulates the storage format of tasks, to decouple the format from other classes. Defines
 * 1. the *taskString* format for Events, Deadlines, and ToDos respectively;
 * 2. How conversion should be carried out between each Task subtype and their respective taskString.
 */
public class StorageParser {
    private static final String EVENT_TASK_TYPE = "E";
    private static final String DEADLINE_TASK_TYPE = "D";
    private static final String TODO_TASK_TYPE = "T";

    private static final String TASK_DONE_INDICATOR = "1";
    private static final String TASK_NOT_DONE_INDICATOR = "0";

    public static Task toTask(String taskString) {
        String[] args = taskString.split(" | ");
        String taskType = args[0];

        switch (taskType) {
            case EVENT_TASK_TYPE:
                return StorageParser.toEvent(args);
            case DEADLINE_TASK_TYPE:
                return StorageParser.toDeadline(args);
            case TODO_TASK_TYPE:
                return StorageParser.toToDo(args);
            default:
                throw new IllegalArgumentException("taskString does not have a valid task type ('E', 'D', 'T').");
        }
    }

    private static Event toEvent(String[] args) {
        boolean isDone = args[1].equals(TASK_DONE_INDICATOR);
        String name = args[2];
        LocalDate date = LocalDate.parse(args[3]);

        Event event = new Event(name, date);
        if (isDone) {
            event.markAsDone();
        }

        return event;
    }

    private static Deadline toDeadline(String[] args) {
        boolean isDone = args[1].equals(TASK_DONE_INDICATOR);
        String name = args[2];
        LocalDate date = LocalDate.parse(args[3]);

        Deadline deadline = new Deadline(name, date);
        if (isDone) {
            deadline.markAsDone();
        }

        return deadline;
    }

    private static ToDo toToDo(String[] args) {
        boolean isDone = args[1].equals(TASK_DONE_INDICATOR);
        String name = args[2];

        ToDo todo = new ToDo(name);
        if (isDone) {
            todo.markAsDone();
        }

        return todo;
    }

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
            throw new IllegalArgumentException("Task does not belong to either Event, Deadline, or ToDo task.");
        }
    }

    private static String toTaskString(Event event) {
        return String.format(
                "%s | %s | %s | %s",
                EVENT_TASK_TYPE,
                event.isDone() ? TASK_DONE_INDICATOR : TASK_NOT_DONE_INDICATOR,
                event.getName(),
                event.getDate().toString()
        );
    }

    private static String toTaskString(Deadline deadline) {
        return String.format(
                "%s | %s | %s | %s",
                DEADLINE_TASK_TYPE,
                deadline.isDone() ? TASK_DONE_INDICATOR : TASK_NOT_DONE_INDICATOR,
                deadline.getName(),
                deadline.getDate().toString()
        );
    }

    private static String toTaskString(ToDo todo) {
        return String.format(
                "%s | %s | %s",
                TODO_TASK_TYPE,
                todo.isDone() ? TASK_DONE_INDICATOR : TASK_NOT_DONE_INDICATOR,
                todo.getName()
        );
    }
}
