public class Event extends Task{
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
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
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
