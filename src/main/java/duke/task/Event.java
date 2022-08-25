package duke.task;
public class Event extends Task {
    private String date;

    public Event(String[] command) {
        super(command[0]);
        this.date = command[1];
    }

    @Override
    public String tofileString() {
        return "E|" + super.tofileString() + "|" + this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + date;
    }
}
