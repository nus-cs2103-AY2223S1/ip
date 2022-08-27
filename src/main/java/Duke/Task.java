package Duke;

/**
 * Class task
 */
public class Task {

    protected final String name;
    protected boolean isDone;

    /**
     * Constructor of task
     *
     * @param name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }


    /**
     * format task stored in file
     *
     * @return string of desired format
     */
    public String fileFormat() {
        if(isDone) {
            return "1|" + this.name;
        }
        return"0|" + this.name;
    }

    /**
     *{@inheritDoc}
     * @return string of desired format
     */
    @Override
    public String toString() {
        if(isDone) {
            return "[X] " + this.name;
        }
        return"[ ] " + this.name;
    }
}
