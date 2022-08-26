package duke;

public class Event extends Task{
    String time;
    String type;
    Event(String name, boolean isDone, String time){
        super(name, isDone);
        this.time = time;
        this.type = "[E]";
    }
    @Override
    public String toString() {
        return this.type + super.getStatus() + " (at: " + this.time + ")";
    }
}
