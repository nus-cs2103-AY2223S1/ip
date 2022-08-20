public abstract class Task {

    private final String name;
    private boolean done;

    public Task(String name, boolean done) throws TaskNoNameException {

        if (name.isEmpty()) {
            throw new TaskNoNameException("The name for a task cannot be empty.");
        }

        this.name = name;
        this.done = done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }

    public String toStorageString() {
        return this.name + "|||" + this.done;
    }

    public static Task createTaskFromStorageString(String taskStr)
            throws TaskNoNameException, IllegalTaskTypeException {

        String[] tokens = taskStr.split("\\|\\|\\|");
        String name = tokens[0];
        boolean done = Boolean.parseBoolean(tokens[1]);
        String taskType = tokens[2];

        switch (taskType) {
        case "todo":
            return new TaskTodo(name, done);
        case "deadline":
            String deadline = tokens[3];
            return new TaskDeadline(name, done, deadline);
        case "event":
            String start = tokens[3];
            String end = tokens[4];
            return new TaskEvent(name, done, start, end);
        default:
            throw new IllegalTaskTypeException("Task type " + taskType + " is invalid.");
        }

    }

}
