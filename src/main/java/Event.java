class Event extends Task {

    String eventTime;
    Event (int id, String name, String eventTime) {
        super(id, name);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String out = super.getId() + ".[E][";
        if (super.getStatus())
            out += "X";
        else
            out += " ";
        out += "] " + super.toString() + "(at : " + eventTime + ")";
        return out;
    }
}
