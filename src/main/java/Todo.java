public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public void done() {
        this.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n " + this.toString()  + "\n");
    }

    @Override
    public void notDone() {
        this.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n " + this.toString() + "\n");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
