public class Event extends Task {
    private final String timing;

    public Event(String input, String timing) {
        super(input, "");
        this.timing = timing;
    }

    public Event(String input, boolean done, String timing) {
        super(input, done, "");
        this.timing = timing;
    }
    @Override
    public String getTiming(){
        return this.timing;
    }

    public Event markDone() {
        return new Event(this.getVal(), true, this.timing);
    }

    public Event markUndone() {
        return new Event(this.getVal(), false, this.timing);
    }

    @Override
    public String toString() {
        if(this.getDone()) {
            return (String.format("[E][X] %s (%s)", this.getVal(), this.getTiming()));
        }
        else {
            return String.format("[E][ ] %s (%s)", this.getVal(), this.getTiming());
        }
    }
}
