public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public void toggleDone(String command) {
        if (command.startsWith("mark ")) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done: \n" + this.toString());
        } else if (command.startsWith("unmark ")) {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet: \n" + this.toString());
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon()  + "] " + description;
    }
}
