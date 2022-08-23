package duke.tasks;

import duke.exceptions.DukeException;

public abstract class Task {
    public enum TaskType {
        TODO("T"),
        DEADLINE("D"),
        EVENT("E");

        public final String value;

        private TaskType(String value) {
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
                System.out.println(str);
                throw new DukeException("Exception: Unknown task type.");
            }
        }
    }
    private String description;
    private TaskType type;
    private boolean isDone;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public TaskType getType() {
        return this.type;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskIcon() {
        return type.value;
    }

    public String getStatusIcon() {
        return this.isDone ? "x" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
