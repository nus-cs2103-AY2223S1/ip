package duke.models.serializable;

import duke.models.task.Event;
import duke.models.task.Deadline;
import duke.models.task.Task;
import duke.models.task.TaskType;
import duke.models.task.ToDo;
import duke.exceptions.DukeException;
import duke.utils.DukeValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Encapsulates the logic for serializing and deserializing a {@link Task}.
 *
 * @author Emily Ong Hui Qi
 */
public class TaskSerializable extends Serializable<Task>{
    private final TaskType taskType;
    private final String taskDescription;
    private final boolean taskIsDone;
    private final Object taskMetaData;

    private static final Pattern MATCH_TASK_DATA = Pattern.compile("(?<taskType>[TDE])\\s\\|\\s(?<taskCompleted>[01])\\s\\|\\s(?<taskDescription>(.+))(\\s\\|\\s(?<taskMeta>(.+)))?");

    private static final String ERROR_UNKNOWN_TASK_TYPE = "Unknown task type %s!";

    /**
     * Initializes the serializable {@link Task} object from parameters for the {@link Task} model.
     *
     * @param taskType The type of the task
     * @param taskDescription The description of the task
     * @param taskIsDone The completion status of the task
     * @param taskMetaData The meta data associated with the task
     */
    public TaskSerializable(
            TaskType taskType,
            String taskDescription,
            boolean taskIsDone,
            Object taskMetaData
    ) {
        super(TaskSerializable.collateTaskInformation(taskType, taskDescription, taskIsDone, taskMetaData));
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskIsDone = taskIsDone;
        this.taskMetaData = taskMetaData;
    }

    private TaskSerializable(String serializedString) throws DukeException {
        super(serializedString, TaskSerializable.MATCH_TASK_DATA);
        String[] originalData = super.get();
        this.taskType = TaskType.fromString(originalData[0]);
        this.taskIsDone = originalData[1].equals("1");
        this.taskDescription = originalData[2];
        this.taskMetaData = originalData.length > 3 ? originalData[3] : null;
    }

    private static String[] collateTaskInformation(
            TaskType taskType,
            String taskDescription,
            boolean taskIsDone,
            Object taskMetaData
    ) {
        String taskIsDoneStatus = taskIsDone ? "1" : "0";
        ArrayList<String> data = new ArrayList<>(){
            {
                add(taskType.toString());
                add(taskIsDoneStatus);
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
     * @param serializedString The received serialized string
     * @return The serializable task object if and only if the received serialized string is of the expected format
     * @throws DukeException If the serialized string is not of the expected format
     */
    public static TaskSerializable from(String serializedString) throws DukeException {
        return new TaskSerializable(serializedString);
    }

    @Override
    public Task deserialize() throws DukeException {
        switch (this.taskType) {
            case TODO:
                return new ToDo(this.taskDescription, this.taskIsDone);
            case DEADLINE:
                LocalDate deadline = DukeValidator.parseDate(this.taskMetaData.toString());
                return new Deadline(this.taskDescription, deadline, this.taskIsDone);
            case EVENT:
                LocalDate date = DukeValidator.parseDate(this.taskMetaData.toString());
                return new Event(this.taskDescription, date, this.taskIsDone);
        }
        throw new DukeException(String.format(TaskSerializable.ERROR_UNKNOWN_TASK_TYPE, this.taskType));
    }
}
