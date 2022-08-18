public class Event extends Task{
    private String dateTime = "";

    public Event(String taskDescription, String dateTime) {
        super(taskDescription.replace("event ", ""));
        this.dateTime = dateTime;
    }
    @Override
    protected String returnDescription() {
        return "[E]" + super.returnDescription() + "(at: " + this.dateTime + ")";
    }
}
