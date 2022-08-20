/**
 * Represents a Task Class.
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
abstract class Task {

    /**
     * Represents the different action types the Task class can handle
     */
    enum Task_type {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Represents whether the task is completed.
     */
    private boolean completed;

    /**
     * Constructor for Task.
     */
    private Task() {
        this.completed = false;
    }

    /**
     * Creates tasks through a factory method.
     *
     * @param x       Type of task
     * @param details Additional details of the task
     * @return Task created
     */
    public static Task of(Task_type x, String details) {
        switch (x) {
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
     * @return String representation of status
     */
    public String checkBox() {
        return this.completed ? "[X]" : "[ ]";
    }

    /**
     * Makes the status of a task complete.
     */
    public void markComplete() {
        this.completed = true;
    }

    /**
     * Makes the status of a task incomplete.
     */
    public void markIncomplete() {
        this.completed = false;
    }

    /**
     * Represents a todo task.
     */
    private static class ToDo extends Task {

        /**
         * Represents the details of what to do.
         */
        private final String todo;

        /**
         * Creates a task through a constructor method.
         *
         * @param s details of task
         */
        private ToDo(String s) {
            super();
            this.todo = s;
        }

        /**
         * Represents the icon to be shown to describe the work type.
         *
         * @return String representation of work type
         */
        @Override
        public String workTypeBox() {
            return "[T]";
        }

        /**
         * Represents a modified version of the Task.
         *
         * @return String representation of the task
         */
        @Override
        public String toString() {
            return this.workTypeBox() + this.checkBox() + " " + todo;
        }
    }

    /**
     * Represents a Deadline task.
     */
    private static class Deadline extends Task {

        /**
         * Represents the details of what to do.
         */
        private final String todo;

        /**
         * Represents the date to complete the task by.
         */
        private final String date;

        /**
         * Returns a Deadline task, a constructor method.
         *
         * @param s details of deadline
         */
        private Deadline(String s) {
            super();
            String[] split = s.split(" /by ");
            this.todo = split[0];
            this.date = split[1];
        }

        /**
         * Represents the icon to be shown to describe the work type.
         *
         * @return String representation of work type
         */
        @Override
        public String workTypeBox() {
            return "[D]";
        }

        /**
         * Represents a modified version of the Task.
         *
         * @return String representation of the task
         */
        @Override
        public String toString() {
            return this.workTypeBox() + this.checkBox() + " " + todo + " (by: " + date + ")";
        }
    }

    /**
     * Represents an Event task.
     */
    private static class Event extends Task {

        /**
         * Represents the details of what to do.
         */
        private final String todo;

        /**
         * Represents the date of event.
         */
        private final String date;

        /**
         * Returns an Event task, a constructor method.
         *
         * @param s details of event
         */
        private Event(String s) {
            super();
            String[] split = s.split(" /at ");
            this.todo = split[0];
            this.date = split[1];
        }

        /**
         * Represents the icon to be shown to describe the work type.
         *
         * @return String representation of work type
         */
        @Override
        public String workTypeBox() {
            return "[E]";
        }

        /**
         * Represents a modified version of the Task.
         *
         * @return String representation of the task
         */
        @Override
        public String toString() {
            return this.workTypeBox() + this.checkBox() + " " + todo + " (at: " + date + ")";
        }
    }

    /**
     * Represents the icon to describe the work type.
     *
     * @return String representation of the work type
     */
    abstract String workTypeBox();
}
