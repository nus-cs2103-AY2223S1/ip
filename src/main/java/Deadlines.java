public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[" + TaskType.D +"]" + "[" + this.getStatusIcon() + "] " + this.getName() +  " (by:" + this.getDeadline() + ")";
    }

    @Override
    public String toCSV() {
        return TaskType.D + "," + this.getStatusIcon()  + "," + this.getName() + "," + this.getDeadline();
    }
}
