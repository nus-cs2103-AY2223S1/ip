package duke.task;

/**
 * Task in the task management.
 */
public class Task {
    private String task;
    private boolean done;

<<<<<<< HEAD
=======
    /**
     * Creates a Task object.
     * @param task Short description of the task.
     */
>>>>>>> 4dd3ea8 (add JavaDocs to some classes for A-JavaDocs)
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

<<<<<<< HEAD
=======
    /**
     * Marks a task as completed.
     * @return The new completion status of the task: true.
     */
>>>>>>> 4dd3ea8 (add JavaDocs to some classes for A-JavaDocs)
    public boolean done() {
        done = true;
        return true;
    }

<<<<<<< HEAD
    public boolean notDone() {
=======
    /**
     * Marks a task as not completed.
     * @return The new completion status of the task: false.
     */
    public boolean notDone(){
>>>>>>> 4dd3ea8 (add JavaDocs to some classes for A-JavaDocs)
        done = false;
        return false;
    }

    /**
     * {@inheritdocs}
     * @return {@inheritdocs}
     */
    @Override
    public String toString() {
        if (done) {
            return " | X | " + task;
        } else {
            return " |   |  " + task;
        }
    }
<<<<<<< HEAD
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
=======
>>>>>>> 4dd3ea8 (add JavaDocs to some classes for A-JavaDocs)
}
