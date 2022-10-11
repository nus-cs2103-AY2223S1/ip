package seedu.duke.task;

/**
 * General class for tasks
 */
public class Task {
    String name;
    boolean isDone;
    String TYPE = null;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for loading list
     * @param name
     * @param isDone
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }


    /**
     * Indicates if a task is done or not
     * @return
     */
    public String getStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getName() {
        return name;
    }
    public String getType() { return TYPE;};

    /**
     * Marks a task as done.
     * @return true if successfully marked done, false if task is already done
     */
    public boolean markDone() {
        if (isDone) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    /**
     * Marks a task as undone
     * @return trust if successfully marked undone, false if task is already undone
     */
    public boolean markUndone() {
        if (!isDone) {
            return false;
        }
        this.isDone = false;
         return true;
    }

    /**
     * Changes the name/description
     * @param newName
     */
    public void changeDesc(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return this.getStatus() + ' ' + this.getName();
    }


}
