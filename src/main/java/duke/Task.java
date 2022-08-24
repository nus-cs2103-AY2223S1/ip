package duke;
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A task message.
     */
    public void addTaskMessage(){
        System.out.println("Gotcha, I've added this task:");
        System.out.println(this.toString());
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
    @Override
    public String toString(){
        return this.isDone ? "[X] " + this.description : "[ ] " + this.description;
    }
}
