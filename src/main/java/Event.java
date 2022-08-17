class Event extends Task {

    Event(String description) {
        super(description);
    }

    public String toString() {
        return "[E]" + super.toString();
    }

}