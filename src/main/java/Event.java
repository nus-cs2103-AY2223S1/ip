public class Event extends Task {
    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    public String getDescription() {
        return super.description;
    }

    public String getEventAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }


}

