public class Event extends Task{
    private String dateTime = "";

    public Event(String taskDescription, String dateTime) {
        super(taskDescription.replace("event ", ""));
        this.dateTime = dateTime;
    }

    public Event(String taskDescription, String dateTime, boolean isCompleted) {
        super(taskDescription, isCompleted);
        this.dateTime = dateTime;
    }

    @Override
    protected String returnDescription() {
        return "[E]" + super.returnDescription() + "(at: " + this.dateTime + ")";
    }

    @Override
    protected String toWriteFile() {
        return "E , " + super.toWriteFile() + ", " + this.dateTime;
    }
}
