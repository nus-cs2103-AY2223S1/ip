public class Task {
    private String name;
    private int num;
    private boolean done;

    public Task(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public boolean isDone() {
        return this.done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    public String toString() {
        if (this.isDone()) {
            return("\t " + this.num + ". [x] " + this.name);
        }
        else {
            return("\t " + this.num + ". [ ] " + this.name);
        }
    }
}
