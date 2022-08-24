public class Event extends Task{
    private String at;
    private String taskType;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "E";
    }


    public String getDescription() {
        return super.getDescription() + " | " + at;
    }


    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
