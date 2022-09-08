package duke.models.serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.models.task.Deadline;
import duke.models.task.Event;
import duke.models.task.Task;
import duke.models.task.TaskType;
import duke.models.task.ToDo;
import duke.utils.DukeValidator;

/**
 * Encapsulates the logic for serializing and deserializing a {@link Task}.
 *
 * @author Emily Ong Hui Qi
 */
public class TaskSerializable extends Serializable<Task> {
    private static final Pattern MATCH_TASK_DATA = Pattern.compile("(?<taskType>[TDE])\\s\\|\\s"
        + "(?<taskCompleted>[01])\\s\\|\\s"
        + "(?<taskDoneAt>(.*))\\s\\|\\s"
        + "(?<taskDescription>(.+))(\\s\\|\\s"
        + "(?<taskMeta>(.+)))?"
    );

    private static final String ASSERTION_SERIALIZED_FORMAT_VALID = "Serialized format must be valid.";

    private static final String ERROR_UNKNOWN_TASK_TYPE = "Unknown task type %s!";

    /**
     * There are 4 core serialized components, including {@code taskType}, {@code taskCompleted},
     * {@code taskDoneAt}, {@code taskDescription} and optionally a {@code taskMeta}, which is not counted.
     */
    private static final int NUM_CORE_SERIALIZED_COMPONENTS = 4;

    private static final String TASK_IS_DONE_STATUS = "1";
    private static final String TASK_IS_NOT_DONE_STATUS = "0";

    private final TaskType taskType;
    private final String taskDescription;
    private final boolean taskIsDone;
    private final Object taskDoneAt;
    private final Object taskMetaData;

    /**
     * Initializes the serializable {@link Task} object from parameters for the {@link Task} model.
     *
     * @param taskType        The type of the task.
     * @param taskDescription The description of the task.
     * @param taskIsDone      The completion status of the task.
     * @param taskDoneAt      The date where the task was marked as done.
     * @param taskMetaData    The meta data associated with the task.
     */
    public TaskSerializable(
        TaskType taskType,
        String taskDescription,
        boolean taskIsDone,
        LocalDate taskDoneAt,
        Object taskMetaData
    ) {
        super(TaskSerializable.collateTaskInformation(taskType, taskDescription, taskIsDone, taskDoneAt, taskMetaData));
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskIsDone = taskIsDone;
        this.taskDoneAt = taskDoneAt;
        this.taskMetaData = taskMetaData;
    }

    private TaskSerializable(String serializedString) throws DukeException {
        super(serializedString, TaskSerializable.MATCH_TASK_DATA);
        String[] originalData = super.get();

        assert (
            originalData.length == TaskSerializable.NUM_CORE_SERIALIZED_COMPONENTS
                || originalData.length == NUM_CORE_SERIALIZED_COMPONENTS + 1
        ) : TaskSerializable.ASSERTION_SERIALIZED_FORMAT_VALID;

        // By invariance, there are at least 4 core serialized components consisting of
        // `taskType`, `taskIsDone`, `taskDoneAt`, and `taskDescription`. Optionally, there is a
        // `taskMeta` component.
        this.taskType = TaskType.fromString(originalData[0]);
        this.taskIsDone = originalData[1].equals(TaskSerializable.TASK_IS_DONE_STATUS);
        this.taskDoneAt = originalData[2];
        this.taskDescription = originalData[3];
        this.taskMetaData = originalData.length > TaskSerializable.NUM_CORE_SERIALIZED_COMPONENTS
            ? originalData[4]
            : null;
    }

    private static String[] collateTaskInformation(
        TaskType taskType,
        String taskDescription,
        boolean taskIsDone,
        LocalDate taskDoneAt,
        Object taskMetaData
    ) {
        String taskIsDoneStatus = taskIsDone
            ? TaskSerializable.TASK_IS_DONE_STATUS
            : TaskSerializable.TASK_IS_NOT_DONE_STATUS;
        ArrayList<String> data = new ArrayList<>() {
            {
                add(taskType.toString());
                add(taskIsDoneStatus);
                add(taskDoneAt == null ? "" : taskDoneAt.toString());
                add(taskDescription);
            }
        };
        if (taskMetaData != null) {
            data.add(taskMetaData.toString());
        }
        return data.toArray(String[]::new);
    }

    /**
     * Initializes and returns the serializable {@link Task} object from a serialized string.
     *
     * @param serializedString The received serialized string.
     *
     * @return The serializable task object if and only if the received serialized string is of the expected format.
     * @throws DukeException If the serialized string is not of the expected format.
     */
    public static TaskSerializable from(String serializedString) throws DukeException {
        return new TaskSerializable(serializedString);
    }

    @Override
    public Task deserialize() throws DukeException {
        LocalDate doneAt = DukeValidator.parseDate(this.taskDoneAt.toString());
        switch (this.taskType) {
        case TODO:
            return new ToDo(this.taskDescription, this.taskIsDone, doneAt);
        case DEADLINE:
            LocalDate deadline = DukeValidator.parseDate(this.taskMetaData.toString());
            return new Deadline(this.taskDescription, deadline, this.taskIsDone, doneAt);
        case EVENT:
            LocalDate date = DukeValidator.parseDate(this.taskMetaData.toString());
            return new Event(this.taskDescription, date, this.taskIsDone, doneAt);
        default:
            throw new DukeException(String.format(TaskSerializable.ERROR_UNKNOWN_TASK_TYPE, this.taskType));
        }
    }
}
