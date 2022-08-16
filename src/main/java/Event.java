public class Event extends Task{
    public String date;
    public Event (String[] command) {
        super(command[0]);
        this.date = command[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + date;
    }
}
