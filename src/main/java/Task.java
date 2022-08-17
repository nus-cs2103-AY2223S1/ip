public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void setDone() {
        if (this.done) {
            System.out.println("Task is already done");
            return;
        }
        this.done = true;
        System.out.println("Task is marked as Done \n" + this.toString());
    }

    public void setNotDone() {
        if (!this.done) {
            System.out.println("Task is still undone");
            return;
        }
        this.done = false;
        System.out.println("Task is marked as undone \n" + this.toString());
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
