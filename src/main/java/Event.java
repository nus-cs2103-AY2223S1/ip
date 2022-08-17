// This class inherits from the abstract Task class
// and encapsulates the logic of an Event task.
public class Event extends Task {

    private String duration;

    public Event (String description) {
        super(description);
        String[] temp = description.split("/at ");
        this.description = temp[0];
        duration = temp.length < 2 ? "No duration given" : temp[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" (at: " + duration + ")";
    }
}
