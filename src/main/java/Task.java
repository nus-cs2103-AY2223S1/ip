public class Task {
    private boolean isDone;
    private String text;

    public Task(boolean isDone, String text) {
        this.isDone = isDone;
        this.text = text;
        System.out.printf("Got it. I've added this task:\n  ");
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', this.text);
    }
}
