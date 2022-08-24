public class Event extends Task {
    private String time;
    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    public Event(String content, String time, boolean isDone) {
        super(content, isDone);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return this.isDone() ? "[E][X] " + this.getContent() + " at " + this.time
                :"[E][ ] " + this.getContent() + " at " + this.time;
    }
}
