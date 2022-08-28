package duke;

public abstract class Task {
    protected String taskName;
    protected boolean done;
    public abstract String taskToFileString();

    static final String TASK_TODO = " T ";
    static final String TASK_EVENT = " E ";
    static final String TASK_DEADLINE = " D ";


    public static Task fileStringToTask(String task) {
        String[] taskSplit = task.split("\\|");
        String type = taskSplit[0];
        switch (type) {
            case TASK_TODO:
                return new Todo(taskSplit[2].trim(), taskSplit[1].equals("1"));
            case TASK_EVENT:
                return new Event(taskSplit[2].trim(), taskSplit[3], taskSplit[1].equals("1"));
            case TASK_DEADLINE:
                return new Deadline(taskSplit[2].trim(), taskSplit[3], taskSplit[1].equals("1"));
            default:
                return null;
        }
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.done = isDone;
    }

    public void mark() {
        this.done = true;
    }

    public void unMark() {
        this.done = false;
    }


}
