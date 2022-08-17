public abstract class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void markTask() {
        done = true;
        System.out.println("Nice! I've marked this task as done:\n" + toString() + "\n");
    }

    public void unmarkTask() {
        done = false;
        System.out.println("OK, I've marked this task as not done yet:\n" + toString() + "\n");
    }

    public boolean getDone() {
        return done;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + task;
        }
        return "[ ] " + task;
    }
}
