package models;

import exceptions.DukeException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TaskSerializable extends Serializable<Task>{
    private final TaskType taskType;
    private final String taskDescription;
    private final boolean taskIsDone;
    private final Object taskMetaData;

    private static final Pattern MATCH_TASK_DATA = Pattern.compile("(?<taskType>[TDE])\\s\\|\\s" +
            "(?<taskCompleted>[01])\\s\\|\\s" +
            "(?<taskDescription>(.+))(\\s\\|\\s" +
            "(?<taskMeta>(.+)))?"
    );

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

    public TaskSerializable(String serializedString) throws DukeException {
        super(serializedString, TaskSerializable.MATCH_TASK_DATA);
        String[] originalData = super.get();
        this.taskType = TaskType.fromString(originalData[0]);
        this.taskDescription = originalData[1];
        this.taskIsDone = originalData[2].equals("1");
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
                add(taskDescription);
                add(taskIsDoneStatus);
            }
        };
        if (taskMetaData != null) {
            data.add(taskMetaData.toString());
        }
        return data.toArray(String[]::new);
    }

    public static TaskSerializable from(String serializedString) throws DukeException {
        return new TaskSerializable(serializedString);
    }

    @Override
    public Task deserialize() {
        switch (this.taskType) {
            case TODO:
                return new ToDo(this.taskDescription, this.taskIsDone);
            case DEADLINE:
                String deadline = this.taskMetaData.toString();
                return new Deadline(this.taskDescription, deadline, this.taskIsDone);
            case EVENT:
                String datetime = this.taskMetaData.toString();
                return new Event(this.taskDescription, datetime, this.taskIsDone);
        }
        return null;
    }
}
