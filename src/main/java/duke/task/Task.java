package duke.task;

import java.time.LocalDate;

/**
 * Represents a Task Class.
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public abstract class Task {

    /**
     * Represents the different action types the Task class can handle.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Represents whether the task is Completed.
     */
    private boolean isCompleted;

    /**
     * Constructor for Task.
     */
    private Task() {
        this.isCompleted = false;
    }

    /**
     * Creates tasks through a factory method.
     *
     * @param taskType Type of task.
     * @param details Additional details of the task.
     * @return Task object created.
     */
    public static Task of(TaskType taskType, String details) {
        switch (taskType) {
        case TODO:
            return new ToDo(details);
        case DEADLINE:
            return new Deadline(details);
        case EVENT:
            return new Event(details);
        default:
            return null;
        }
    }

    /**
     * Represents what should be shown to describe the task's completion status.
     *
     * @return String representation of status.
     */
    public String getCheckBox() {
        return this.isCompleted ? "[X]" : "[ ]";
    }

    /**
     * Makes the status of a task complete.
     */
    public void markComplete() {
        this.isCompleted = true;
    }

    /**
     * Makes the status of a task incomplete.
     */
    public void markIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Checks if a task is completed.
     */
    public boolean checkIfCompleted() {
        return this.isCompleted;
    }

    /**
     * Represents the completion status to be added into the file.
     *
     * @return String representation of status.
     */
    public String getCompletionStatusForFile() {
        return this.isCompleted ? "1" : "0";
    }

    /**
     * Crafts message for file given various parameters.
     *
     * @param arr Array consisting of various strings.
     * @return String message.
     */
    public String craftStringForFile(String[] arr) {
        int length = arr.length;
        String res = "";
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                res += arr[i];
            } else {
                res += " | " + arr[i];
            }
        }
        return res;
    }

    /**
     * Represents a toDo task.
     */
    private static class ToDo extends Task {

        /**
         * Represents the details of what to do.
         */
        private final String toDo;

        /**
         * Creates a task through a constructor method.
         *
         * @param toDo Details of task.
         */
        private ToDo(String toDo) {
            super();
            this.toDo = toDo;
        }

        /**
         * Represents the icon to be shown to describe the work type.
         *
         * @return String representation of work type.
         */
        @Override
        public String getWorkTypeBox() {
            return "[T]";
        }

        /**
         * Represents a modified version of the Task.
         *
         * @return String representation of the task.
         */
        @Override
        public String toString() {
            return this.getWorkTypeBox() + this.getCheckBox() + " " + toDo;
        }

        /**
         * Crafts message to be added into text file.
         *
         * @return String message.
         */
        @Override
        public String constructTextFileMessage() {
            return craftStringForFile(new String[]{"T", getCompletionStatusForFile(), this.toDo});
        }

        /**
         * Checks whether task contains certain String.
         * @param s String.
         * @return Boolean.
         */
        @Override
        public boolean containString(String s) {
            return this.toDo.contains(s);
        }

        /**
         * Gets the task type of task.
         * @return TaskType.
         */
        @Override
        public TaskType getTaskType() {
            return TaskType.TODO;
        }
    }

    /**
     * Represents a Deadline task.
     */
    private static class Deadline extends Task {

        /**
         * Represents the details of what to do.
         */
        private final String toDo;

        /**
         * Represents the date to complete the task by.
         */
        private final LocalDate date;

        /**
         * Returns a Deadline task, a constructor method.
         *
         * @param s Details of deadline.
         */
        private Deadline(String s) {
            super();
            String[] split = s.split(" /by ");
            this.toDo = split[0];
            this.date = LocalDate.parse(split[1]);
        }

        /**
         * Represents the icon to be shown to describe the work type.
         *
         * @return String representation of work type.
         */
        @Override
        public String getWorkTypeBox() {
            return "[D]";
        }

        /**
         * Represents a modified version of the Task.
         *
         * @return String representation of the task.
         */
        @Override
        public String toString() {
            return this.getWorkTypeBox() + this.getCheckBox() + " " + toDo + " (by: " + date + ")";
        }

        /**
         * Crafts message to be added into text file.
         *
         * @return String message.
         */
        @Override
        public String constructTextFileMessage() {
            return craftStringForFile(new String[]
                {"D", getCompletionStatusForFile(), this.toDo, String.valueOf(this.date)});
        }

        /**
         * Checks whether task object contains certain String.
         * @param s String.
         * @return Boolean.
         */
        @Override
        public boolean containString(String s) {
            return this.toDo.contains(s) || this.date.toString().contains(s);
        }

        /**
         * Gets the task type of task.
         * @return TaskType.
         */
        @Override
        public TaskType getTaskType() {
            return TaskType.DEADLINE;
        }
    }

    /**
     * Represents an Event task.
     */
    private static class Event extends Task {

        /**
         * Represents the details of what to do.
         */
        private final String toDo;

        /**
         * Represents the date of event.
         */
        private final LocalDate date;

        /**
         * Returns an Event task, a constructor method.
         *
         * @param s Details of event.
         */
        private Event(String s) {
            super();
            String[] split = s.split(" /at ");
            this.toDo = split[0];
            this.date = LocalDate.parse(split[1]);
        }

        /**
         * Represents the icon to be shown to describe the work type.
         *
         * @return String representation of work type.
         */
        @Override
        public String getWorkTypeBox() {
            return "[E]";
        }

        /**
         * Represents a modified version of the Task.
         *
         * @return String representation of the task.
         */
        @Override
        public String toString() {
            return this.getWorkTypeBox() + this.getCheckBox() + " " + toDo + " (at: " + date + ")";
        }

        /**
         * Crafts message to be added into text file.
         *
         * @return String message.
         */
        @Override
        public String constructTextFileMessage() {
            return craftStringForFile(new String[]
                {"E", getCompletionStatusForFile(), this.toDo, String.valueOf(this.date)});
        }

        /**
         * Checks whether task object contains certain String.
         * @param s String.
         * @return Boolean.
         */
        @Override
        public boolean containString(String s) {
            return this.toDo.contains(s) || this.date.toString().contains(s);
        }

        /**
         * Gets the task type of task.
         * @return TaskType.
         */
        @Override
        public TaskType getTaskType() {
            return TaskType.EVENT;
        }
    }

    /**
     * Represents the icon to describe the work type.
     *
     * @return String representation of the work type.
     */
    abstract String getWorkTypeBox();

    /**
     * Crafts message to be added into text file.
     *
     * @return String message.
     */
    public abstract String constructTextFileMessage();

    /**
     * Checks whether task object contains certain String.
     * @param s String.
     * @return Boolean.
     */
    public abstract boolean containString(String s);

    /**
     * Gets the task type of task.
     * @return TaskType.
     */
    public abstract TaskType getTaskType();
}
