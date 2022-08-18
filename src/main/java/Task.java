public class Task {

    private String task;
    private int id;
    private static int counter = 0;
    private boolean done;

    public Task(String t) {
        task = t;
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
        if(done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    protected void toggleStatus() {
        done = !done;
    }

    protected void mark() {
        System.out.println("\tThis task has been marked as done -");
        toggleStatus();
        System.out.println("\t\t" + getStatus() + " " + task);
    }

    protected void unmark() {
        System.out.println("\tThis task has been marked as not done -");
        toggleStatus();
        System.out.println("\t\t" + getStatus() + " " + task);
    }

}
