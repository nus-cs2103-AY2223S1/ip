public class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n [X] " + this.taskDescription + "\n");
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n [ ] " + this.taskDescription + "\n");
    }

    public String addedString() {
        return "added: " + this.taskDescription;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.taskDescription;
    }
}
