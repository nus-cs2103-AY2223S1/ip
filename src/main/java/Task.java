public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        if (this.isDone) {
            System.out.println("Task is already done");
            return;
        }
        this.isDone = true;
        System.out.println("Task is marked as Done \n" + this.toString());
    }

    public void setNotDone() {
        if (!this.isDone) {
            System.out.println("Task is still undone");
            return;
        }
        this.isDone = false;
        System.out.println("Task is marked as undone \n" + this.toString());
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
