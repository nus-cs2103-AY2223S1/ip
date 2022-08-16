class Event extends Task{
    protected String time;

    public Event(String itself, String time) {
        super(itself);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
