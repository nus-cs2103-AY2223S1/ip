package duke.task;

public class Task {
    private String task;
    private boolean done;

    public boolean done() {
        done = true;
        return true;
    }

    public boolean notDone(){
        done = false;
        return false;
    }

    @Override
    public String toString(){
        if (done) {
            return " | X | " + task;
        } else {
            return " |   |  " + task;
        }
    }

    /**
     * Returns true if the description is contained in the task desc, false otherwise.
     * @param desc Description searching for.
     * @return True if desc can be found, false otherwise.
     */
    public boolean contains(String desc) {
        return task.contains(desc);
    }

    public Task(String task) {
        this.task = task;
        this.done = false;
    }
}
