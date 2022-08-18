public class Event extends Task{
    String preposition;
    String timing;

    public Event(String description, String preposition, String timing) {
        super(description);
        this.preposition = preposition;
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + preposition + ": " + timing + ")";
    }
}
