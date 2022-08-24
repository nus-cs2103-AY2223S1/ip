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
 * TODO: Add JavaDocs
 */
public class TaskSerializable extends Serializable<Task> {
    private static final Pattern MATCH_TASK_DATA = Pattern.compile("(?<taskType>[TDE])\\s\\|\\s"
            + "(?<taskCompleted>[01])\\s\\|\\s"
            + "(?<taskDescription>(.+))(\\s\\|\\s"
            + "(?<taskMeta>(.+)))?"
    );

    private static final String ERROR_UNKNOWN_TASK_TYPE = "Unknown task type %s!";

    private final TaskType taskType;
    private final String taskDescription;
    private final boolean taskIsDone;
    private final Object taskMetaData;

    /**
     * TODO: Add JavaDocs
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

    /**
     * TODO: Add JavaDocs
     */
    public TaskSerializable(String serializedString) throws DukeException {
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
        ArrayList<String> data = new ArrayList<>() {
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
     * TODO: Add JavaDocs
     */
    public static TaskSerializable from(String serializedString) throws DukeException {
        return new TaskSerializable(serializedString);
    }

    /**
     * TODO: Add JavaDocs
     */
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
        default:
            throw new DukeException(String.format(TaskSerializable.ERROR_UNKNOWN_TASK_TYPE, this.taskType));
        }
    }
}
