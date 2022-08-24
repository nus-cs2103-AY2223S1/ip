package duke.tasks;

import duke.exceptions.DukeException;

public abstract class Task {
    public enum TaskType {
        TODO("T"),
        DEADLINE("D"),
        EVENT("E");

        public final String value;

        TaskType(String value) {
            this.value = value;
        }

        public static TaskType parseTaskType(String str) throws DukeException {
            switch (str) {
            case "T":
                return TODO;
            case "D":
                return DEADLINE;
            case "E":
                return EVENT;
            default:
                throw new DukeException("Exception: Unknown task type.");
            }
        }
    }
    private String description;
    private TaskType taskType;
    private boolean isDone;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskIcon() {
        return taskType.value;
    }

    public String getStatusIcon() {
        return this.isDone ? "x" : " ";
    }

//    public void mark() {
//        this.isDone = true;
//    }
//
//    public void unmark() {
//        this.isDone = false;
//    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
