public class Task {
    private boolean isMarked = false;
    private String description = "";

    public Task(String description) {
        this.description = description;
    }

    public String markTask() {
        this.isMarked = true;
        return "Nice! I've marked this task as done:\n\t    " + this.toString();
    }

    public String unmarkTask() {
        this.isMarked = false;
        return "OK, I've marked this task as not done yet:\n\t    " + this.toString();
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isMarked ? 'X' : ' ', description);
    }
}
