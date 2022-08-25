public class Deadline extends Task{

    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D] " + (super.isCompleted() ? "[X] " : "[ ] ") + super.getTaskName() + "(by:" + this.date + ")";
    }

    public String getTime() {
        return this.date;
    }

}
