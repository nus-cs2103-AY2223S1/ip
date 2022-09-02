package neo;
public class Task {
    protected String description;
    protected Boolean isDone = false;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * return description of task
     *
     * @return String
     */
    public String getTask() {
        return description;
    }
    /**
     * return X if task has been completed
     *
     * @return String
     */
    public String getIsDone(){
        return (isDone ? "X" : " ");
    }
    /**
     * marks task as done
     *
     * @param done true if task is completed
     */
    public void setIsDone(Boolean done){
        isDone = done;
    }
    @Override
    public String toString() {
        return "[" + getIsDone() + "] " + description;
    }
}

