public class Task {

    private String description;
    private boolean isDone = false;
    protected String due; // can be accessed by subclasses
    protected final String commandWord;

    public Task (String description, String due, String commandWord) {
        this.description = description;
        this.due = due;
        this.commandWord = commandWord;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String formatTaskBeforeSave() {
        String isTaskDone = this.isDone ? "Done" : "Not Done";
        String tag = "";

        if (this instanceof Event) {
            tag = ((Event) this).tag;
        } else if (this instanceof Deadline) {
            tag = ((Deadline) this).tag;
        } else if (this instanceof ToDo) {
            tag = ((ToDo) this).tag;
        }

        String taskToString = tag + " | " + isTaskDone + " | " + this.description + " | " + this.due;
        return taskToString;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

//    public boolean taskDone() {
//        return this.isDone;
//    }

}

