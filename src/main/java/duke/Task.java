package duke;
public class Task {
    private String description;
    private boolean isDone;

    /**
     * A task message.
     */
    public String addTaskMessage(){
        String res = "Gotcha, I've added this task:\n";
        res += this + "\n";
        return res;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * A setter to change the task to be done.
     */
    public void setDone(){
        this.isDone = true;
    }

    /**
     * A setter to change the task to be undone.
     */
    public void setUndone(){
        this.isDone = false;
    }

    /**
     * String representation of the current task.
     * @return The task description and whether the task is done as a string.
     */
    public String getDescription() {
        return this.description;
    }
    
    @Override
    public String toString(){
        return this.isDone ? "[X] " + this.description : "[ ] " + this.description;
    }
}
