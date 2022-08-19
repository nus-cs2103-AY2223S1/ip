public class Event extends Task {
    private String dateTime;

    public Event(String taskText) {
        super(taskText.substring(0, taskText.indexOf('/')));
        this.dateTime = taskText.substring(taskText.indexOf('/')+3);
    }

    @Override
    public String toString(){
        return "[E] " + super.toString() + " (at: " + dateTime + ")";
    }
}
