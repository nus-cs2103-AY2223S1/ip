public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void toggleDone(String command) {
        if (command.startsWith("mark ")) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done: \n" + printTask());
        } else if (command.startsWith("unmark ")) {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet: \n" + printTask());
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String printTask() {
        return "[" + this.getStatusIcon()  + "] " + description;
    }
}
