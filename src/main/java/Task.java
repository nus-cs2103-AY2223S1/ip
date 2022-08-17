public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return description;
    }

    public static String newTask() {
        return "Got it. I've added this task: ";
    }

    public String mark() {
        this.isDone = true;
        return "Nice! I've marked this task as done: \n" + this;

    }

    public String unmark() {
        this.isDone = false;
        return "Ok! I've marked this task as not done yet: \n" + this;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + getDescription();
    }

}