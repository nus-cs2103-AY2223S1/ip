public class Event extends Task {

    public Event(String desc) {
        super(desc);
        super.description = desc.replace("/at", "(at:");
        super.description = super.description + ")";
        super.isDone = false;
        super.type = "E";
    }
}
