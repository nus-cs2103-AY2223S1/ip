public class Event extends Task {

    private String time;

    public Event(String title, String time) {
        super(title, "event");
        this.time = time;
    }

    @Override
    public String toString(){
        return (super.toString() + " (at: " + this.time + ")" );
    }
}
