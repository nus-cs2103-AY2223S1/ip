package cinnamon.Tasks;

/**
 * Class task
 */
public class Task {

    protected final String name;
    protected boolean isDone;
    protected boolean isTagged;
    protected String tag;

    /**
     * Constructor of task
     *
     * @param name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.isTagged = false;
        this.tag = "";
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
     * Sets task as tagged
     */
    public void tagged() {
        this.isTagged = true;
    }

    /**
     * Sets task as untagged
     */
    public void unTag() {
        this.isTagged = false;
    }

    /**
     * Tags task
     *
     * @param tag tag added to the task
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the tag of the task
     *
     * @return string of the task's tag
     */
    public String getTag() {
        return this.tag;
    }

    public boolean isTagged() {
        return isTagged;
    }


    /**
     * formats task stored in file
     *
     * @return string of desired format
     */
    public String fileFormat() {
        if(isDone && isTagged) {
            return "1|" + this.name + "|" + this.tag;
        } else if(!isDone && isTagged) {
            return "0|" + this.name + "|" + this.tag;
        } else if(isDone && !isTagged) {
            return "1|" + this.name;
        }
        return "0|" + this.name;
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
