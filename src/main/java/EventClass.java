public class EventClass extends Task {

    String dateTime;

    protected EventClass(String taskTitle, String dateTime) {
        super(taskTitle, TaskType.EVENT);
        this.dateTime = dateTime;
    }
}
