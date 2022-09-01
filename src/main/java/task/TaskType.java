package task;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum to record different task types
 */
public enum TaskType {
    TODO("TODO"),
    EVENT("EVENT"),
    DEADLINE("DEADLINE");

    private static final Map<String, TaskType> STRING_TASK_TYPE_MAP = new HashMap<>();

    static {
        for (TaskType typeEnum: TaskType.values()) {
            STRING_TASK_TYPE_MAP.put(typeEnum.type, typeEnum);
        }
    }

    public final String type;

    TaskType(String type) {
        this.type = type;
    }

    /**
     * Returns task type enum given the correct string
     * @param typeString String corresponding to a task type
     * @return The appropriate task type enum
     */
    public static TaskType getTypeByString(String typeString) {
        return STRING_TASK_TYPE_MAP.get(typeString);
    }
}
