public class Task {

    private String task;
    private int id;
    private static int counter = 0;
    private boolean done;

    public Task(String task) {
        this.task = task;
        counter ++;
        id = counter;
        done = false;
    }

    protected String getTask() {
        return task;
    }

    protected int getId() {
        return id;
    }

    protected String getStatus() {
        return done ? "[X]" : "[ ]";
    }

    protected void toggleStatus() {
        done = !done;
    }

    protected void mark() {
        System.out.println("\tThis task has been marked as done -");
        toggleStatus();
        System.out.println("\t\t" + this);
    }

    protected void unmark() {
        System.out.println("\tThis task has been marked as not done -");
        toggleStatus();
        System.out.println("\t\t" + this);
    }

    @Override
    public String toString() {
        return getStatus() + " " + task;
    }

}
