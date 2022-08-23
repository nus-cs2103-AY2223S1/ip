public class Event extends Task {

    private String time;

    public Event(String title, String time, boolean done) {
        super(title, "event", done);
        this.time = time;
    }

    @Override
    public String toString(){
        return (super.toString() + " (at: " + this.time + ")" );
    }

    public String getTime(){
        return this.time;
    }
}
