
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Event addTask(String name, String at) {
        Event newEvent = new Event(name, at);
        System.out.println("       " + newEvent.printSelf());
        return newEvent;
    }

    @Override
    public String printSelf() {
        return "[E]" + super.printSelf() + " (at: " + this.at + ")";
    }

}
