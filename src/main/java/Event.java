public class Event extends Task {

    private String at;

    public Event(String name, boolean isDone, String at) {
        super(name, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[E]" + temp + " (at: " + at + ")";
    }


}
