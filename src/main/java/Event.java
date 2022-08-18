public class Event extends Task {
    private String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    @Override
    public String getStatusIcon() {
        return (isDone ? "[E][X]" : "[E][ ]"); // mark done task with X
    }

    @Override
    public String getDescription() {
        return String.format("%s(%s)", this.description, this.time);
    }

}
