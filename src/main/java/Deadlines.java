public class Deadlines extends Task {
    public String command;
    public int num;
    public String time;

    public Deadlines(String description, int num, String time) {
        super(description);
        this.command = description;
        this.num = num;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "[D][ ] " + this.command + "(by: " + time + ")\n"
                + "Now you have " + this.num + " tasks in the list.";
    }
}
