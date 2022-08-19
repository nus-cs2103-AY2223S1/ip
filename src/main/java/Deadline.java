public class Deadline extends Task {
    private String dateTime;

    public Deadline(String taskText) {
        super(taskText.substring(0, taskText.indexOf('/')));
        this.dateTime = taskText.substring(taskText.indexOf('/')+3);
    }

    @Override
    public String toString(){
        return "[D] " + super.toString() + " (by: " + dateTime + ")";
    }
}