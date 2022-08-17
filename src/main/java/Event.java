public class Event extends Todo{
    private String date;
    public Event(String title, String date) {
        super(title);
        this.date = date;
    }
    public String toString() {
        return String.format("%s (at: %s)", super.toString().replace("[T]", "[E]"), this.date);
    }
}
