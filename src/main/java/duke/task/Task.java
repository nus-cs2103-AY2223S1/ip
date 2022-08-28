package duke.task;

public class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public boolean done() {
        done = true;
        return true;
    }

    public boolean notDone() {
        done = false;
        return false;
    }

    @Override
    public String toString() {
        if (done) {
            return " | X | " + task;
        } else {
            return " |   |  " + task;
        }
    }
<<<<<<< HEAD

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
=======
>>>>>>> b68d198 (amend code based on coding style for A-CodingStyle, and integrate stylechecks for A-Stylecheck)
}
