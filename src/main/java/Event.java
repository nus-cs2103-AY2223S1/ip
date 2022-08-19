public class Event extends Task{
    private String dateAndTime;

    public Event(String name, String dateAndTime) {
        super(name);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        if(isDone()) {
            return "[E][X] " + getName()+ " (by: " + this.dateAndTime + ")";
        } else {
            return "[E][ ] " + getName() + " (by: " + this.dateAndTime + ")";
        }
    }
}
