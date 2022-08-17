public class Task {
    private String task;
    private boolean isComplete = false;

    /**
     * Constructor to create a new Task
     *
     * @param task  the task that you want to complete (String)
     */
    public Task(String task) {
        this.task = task;
        System.out.println("added: " + this.task);
    }

    /**
     * Method used to mark this task as complete
     */
    public void markAsDone() {
        this.isComplete = true;
        System.out.println(this.toString());
    }

    /**
     * Method used to mark this task as incomplete
     */
    public void unmark() {
        this.isComplete = false;
        System.out.println(this.toString());
    }

    /**
     * To String method that returns the task in string form to the user
     *
     * @return  the task in string format
     */
    @Override
    public String toString() {
        String checkBox = this.isComplete ? "[X] " : "[ ] ";
        return checkBox + this.task;
    }
}
