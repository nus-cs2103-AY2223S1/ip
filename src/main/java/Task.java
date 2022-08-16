public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String mark() {
        this.isDone = true;
        return " Great job! I will mark the task as completed.\n" +
                "   " + this.toString();
    }

    public String unmark() {
        this.isDone = false;
        return " Understood. I will mark the task as uncompleted.\n" +
                "   " + this.toString();
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[" + symbol + "] " + this.description + "\n";
    }
}
