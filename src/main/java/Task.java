public abstract class Task {
    protected String taskName;
    protected boolean done;
    public abstract String taskToFileString();

    public static Task fileStringToTask(String task) {
        String[] taskSplit = task.split("\\|");
        String type = taskSplit[0];
        switch (type) {
            case " T ":
                return new Todo(taskSplit[2], taskSplit[1].equals("1"));
            case " E ":
                return new Event(taskSplit[2], Utility.dateParser(taskSplit[3]), taskSplit[1].equals("1"));
            case " D ":
                return new Deadline(taskSplit[2], Utility.dateParser(taskSplit[3]), taskSplit[1].equals("1"));
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
