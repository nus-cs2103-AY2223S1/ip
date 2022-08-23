import java.util.Map;

abstract class Task {
    private final String description;
    private final char symbol;
    private boolean isDone;

    /**
     * Initialises a Task with a symbol and description.
     * 
     * @param symbol The char representing the Task's type.
     * @param description A string representing the Task's description.
     */
    private Task(char symbol, String description) {
        this.symbol = symbol;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialises a Task with its type and arguments to be supplied to the constructor.
     *
     * @param taskType The string representing the Task's type.
     * @param args The array of strings representing the arguments to be supplied to the constructor.
     * @return The task object.
     */
    public static Task of(String taskType, Map<String, String> args) {
        switch (taskType) {
        case "todo":
            return new TodoTask(args);
        case "deadline":
            return new DeadlineTask(args);
        case "event":
            return new EventTask(args);
        default:
            return new OtherTask(args);
        }
    }

    /**
     * Returns the char representation of whether a Task is done.
     *
     * @return The char representing the Task's status.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the Task's isDone to isDone.
     *
     * @param isDone The boolean to set the Task's isDone to.
     */
    protected void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of a Task.
     *
     * @return The string representing the Task.
     */
    @Override
    public String toString() {
        return String.format("[%c][%s] %s", this.symbol, this.getStatusIcon(), this.description);
    }

    private static class TodoTask extends Task {
        public TodoTask(Map<String, String> args) {
            super('T', args.get("description"));
        }
    }

    private static class DeadlineTask extends Task {
        private final String deadline;

        public DeadlineTask(Map<String, String> args) {
            super( 'D', args.get("description"));
            this.deadline = args.get("by");
        }

        @Override
        public String toString() {
            return String.format("%s (by: %s)", super.toString(), deadline);
        }
    }

    private static class EventTask extends Task {
        private final String time;

        public EventTask(Map<String, String> args) {
            super( 'E', args.get("description"));
            this.time = args.get("at");
        }

        @Override
        public String toString() {
            return String.format("%s (at: %s)", super.toString(), time);
        }
    }

    private static class OtherTask extends Task {
        public OtherTask(Map<String, String> args) {
            super('O', args.get("description"));
        }
    }
}
