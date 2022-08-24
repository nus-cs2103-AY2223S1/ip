package Duke;

public class Event extends Task {

    String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String storeToString() {
        return "E|" + this.binarytoString() + "|" + this.description.substring(0,description.length()-1) + "|" + this.time;
    }

    @Override
    public String toString() {
        return "[E]"  + super.toString() + "(at: " + this.time + ")";
    }
}
