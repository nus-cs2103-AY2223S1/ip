package duke.entities;

public class Deadline extends Event {
    public Deadline(String desc, String deadline) {
        super(desc, deadline);
    }

    @Override
    public String toString() {
        String marker = isDone() ? "[X] " : "[ ] ";
        return "[D]" + marker + getDescription() + getDeadline();
    }
}
