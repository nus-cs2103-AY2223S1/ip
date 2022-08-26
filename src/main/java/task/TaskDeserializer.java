package task;

import java.time.LocalDateTime;

import org.json.JSONObject;


/**
 * Class that deserialises tasks - needs to be changed each time a task format is updated or added
 */
public class TaskDeserializer {
    static Task deserializeTaskString(String taskString) {
        JSONObject taskObj = new JSONObject(taskString);

        String taskType = taskObj.getString("taskType");
        String description = taskObj.getString("description");
        boolean isDone = taskObj.getBoolean("isDone");

        switch (TaskType.getTypeByString(taskType)) {
        case TODO:
            return new Todo(description, isDone);
        case EVENT:
            return new Event(description, taskObj.getString("at"), isDone);
        case DEADLINE:
            LocalDateTime by = LocalDateTime.parse(taskObj.getString("by"), Deadline.DATE_FORMATTER);
            return new Deadline(description, by, isDone);
        default:
            return null;
        }
    }
}
