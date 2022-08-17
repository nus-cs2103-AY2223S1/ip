public class Event extends Task {
    private String time;
    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        return this.isDone() ? "[E][X] " + this.getContent() + " at " + this.time
                :"[E][ ] " + this.getContent() + " at " + this.time;
    }
}
