public class Event extends Task{
    protected String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
        System.out.println("Making you a more EVENTful person!\n" + this.toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
