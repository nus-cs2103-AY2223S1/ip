public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Prints the completion status and the description of the task.
     *
     * @return The completion status and description of the task.
     */
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        String line = String.format("[%s] %s", status, description);
        return line;
    }

    /**
     * Marks the task as completed and prints the task's description.
     * If the task was already marked as completed, tell the user.
     */
    public void mark() {
        if (this.isDone) {
            System.out.println("Duke: This task has already been marked as done.");
            return;
        }
        this.isDone = true;
        System.out.println("Duke: Nice! I've marked this task as done:");
        System.out.println(this);
    }

    /**
     * Marks the task as completed and prints the task's description.
     * If the task was already marked as completed, tell the user.
     */
    public void unmark() {
        if (!this.isDone) {
            System.out.println("Duke: This task has already been marked as not done.");
            return;
        }
        this.isDone = false;
        System.out.println("Duke: OK, I've marked this task as not done yet:");
        System.out.println(this);
    }
}
