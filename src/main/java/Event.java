public class Event extends Task{

    private String date;

    public Event(String item, String date) {
        super(item);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "at:" + date;
    }
}
