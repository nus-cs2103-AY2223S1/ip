public class Task {
    private String title;
    private boolean completed;

    Task(String title) {
        this.title = title;
        this.completed = false;
    }

    public void markAsCompleted() {
        this.completed = true;
        System.out.println(String.format("Nice! I've marked this task as done:\n\t%s", this));
    }

    public void markAsIncomplete() {
        this.completed = false;
        System.out.println(String.format("OK, I've marked this task as not done yet:\n\t%s", this));
    }

    private String getStatusIcon() {
        return (this.completed ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.title);
    }

}
