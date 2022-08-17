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
     * To String method that returns the task in string form to the user
     *
     * @return  the task in string format
     */
    @Override
    public String toString() {
        return this.task;
    }
}
