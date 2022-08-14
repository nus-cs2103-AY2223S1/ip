public class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Prints the completion status and the description of the task.
     *
     * @return The completion status and description of the task.
     */
    @Override
    public String toString() {
        String status = " ";
        if (this.completed) {
            status = "X";
        }
        String line = String.format("[%s] %s", status, description);
        return line;
    }

    /**
     * Marks the task as completed and prints the task's description.
     * If the task was already marked as completed, tell the user.
     */
    public void mark() {
        if (this.completed) {
            System.out.println("This task has already been marked as done.");
            return;
        }
        this.completed = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    /**
     * Marks the task as completed and prints the task's description.
     * If the task was already marked as completed, tell the user.
     */
    public void unmark() {
        if (!this.completed) {
            System.out.println("This task has already been marked as not done.");
            return;
        }
        this.completed = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }
}
