/*
Class Task
Description: An encapsulation of what a completable task is in the context of Duke.
Has description and isDone field to describe and mark completion status of a task.

@param String description Textual description of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // String representation of the isDone field.
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //Setter for isDone field
    public void setIsDone(Boolean done) {
        this.isDone = done;
    }

    // Getter for isDone field
    public boolean getIsDone() {
        return this.isDone;
    }

    // Getter for description field
    public String getDescription() {
        return this.description;
    }

    // toString method for easy display on the console.
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
