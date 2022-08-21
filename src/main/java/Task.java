public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets completion status of this task to true.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Prints the completion status and the description of the task.
     *
     * @return The completion status and description of the task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        String line = String.format("[%s] %s", status, this.description);
        return line;
    }

    /**
     * Returns the command to be saved in the save file.
     *
     * @return The String representing the command of this task.
     */
    public String toCommand() {
        String completionStatus = this.isDone ? "1" : "0";
        return completionStatus + " | " + this.description;
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
        completeTask();
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
