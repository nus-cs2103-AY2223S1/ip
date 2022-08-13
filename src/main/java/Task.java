public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    void mark() {
        done = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                this.toString());
    }

    void unmark() {
        done = false;
        System.out.println("OK, I've marked this task as not done yet:\n" +
                this.toString());
    }

    String getDescription() {
        return this.description;
    }

    public String toString() {
        if (done) {
            return "[X] "  + description;
        } else {
            return "[ ] " + description;
        }
    }
}
