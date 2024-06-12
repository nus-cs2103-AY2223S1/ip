package duke.task;

/**
 * An enum class that enumerates all possible concrete types of Task, convenient for future development.
 * It can serve as reflection when we need to know the run-time type of a Task.
 */
public enum TaskType {
    TODO, EVENT, DEADLINE;
}
