package main.java.duke;

/**
 * Represents the job to be done, and whether it is done
 *
 * @author eugeneleong
 * @version 1.0
 */

public class Task {
    protected String action;
    protected boolean isDone;

    public Task(String action) {
        this.action = action;
        this.isDone = false;
    }

    /**
     * The job has been done
     */
    public void markIsDone() {
        this.isDone = true;
    }

    /**
     * Uh-oh, the job has not been done
     */
    public void unmarkIsDone() {
        this.isDone = false;
    }

    /**
     * Getter to get the action description
     * @return the description of the action
     */ 
    public String getAction() { return this.action; }

    /**
     * Getter to get the action status
     * @return the status of the action
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the generic description of a task
     * @return [marked?] description
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.action;
    }

}
