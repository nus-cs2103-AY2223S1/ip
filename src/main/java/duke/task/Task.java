package duke.task;

/**
 * Abstract duke.task.Task class encapsulates all the different types of tasks
 * @author Shaune Ang
 */
abstract public class Task {
    private String name;
    private Boolean isCompleted = false;

    /**
     * duke.task.Task constructor for tasks created by user
     * @param name name of task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * duke.task.Task constructor for tasks loaded from saved file
     * @param name name of task
     * @param isCompleted Completion status of task
     */
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Changes isCompleted to done
     */
    public void mark() {
        String message;
        if (isCompleted == true) {
            message = "\t This task is already marked:";
        } else {
            isCompleted = true;
            message = "\tGood Job on completing the task! I've marked this task as done:";
        }
        System.out.println(message);
        System.out.println("\t   " + toString());
    }

    /**
     * Changes isCompleted to not done
     */
    public void unmark() {
        String message;
        if (isCompleted == false) {
            message = "\t This task is already unmarked:";
        } else {
            isCompleted = false;
            message = "\tOK, I've marked this task as not done yet:";
        }
        System.out.println(message);
        System.out.println("\t   " + toString());
    }

    /**
     * Returns name of task and its status to be used when list is called
     * @return Returns String of name of task formatted with status showing
     */
    @Override
    public String toString() {
        String mark = isCompleted ? "X" : " ";
        return String.format("[%s] %s", mark, name);
    }

    /**
     * Returns completion status of task
     * @return completion status of task
     */
    public boolean getStatus() {
        return isCompleted;
    }

    /**
     * Returns name of task
     * @return name of task
     */
    public String getName() {
        return name;
    }
}
