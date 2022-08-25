package task;

import org.json.JSONObject;

/**
 * Class that deserialises tasks - needs to be changed each time a task format is updated or added
 */
public class TaskDeserializer {
    static Task deserializeTaskString(String taskString) {
        JSONObject taskObj = new JSONObject(taskString);

        String taskType = taskObj.getString("taskType");
        String description = taskObj.getString("description");

        switch (TaskType.getTypeByString(taskType)) {
        case TODO:
            return new Todo(description);
        case EVENT:
            return new Event(description, taskObj.getString("at"));
        case DEADLINE:
            return new Deadline(description, taskObj.getString("by"));
        default:
            return null;
        }
    }
}
