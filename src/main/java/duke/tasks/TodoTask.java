package duke.tasks;

public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[" + TaskType.T +"]" + "[" + this.getStatusIcon() + "] " + this.getName();
    }
    @Override
    public String toCSV() {
        return TaskType.T + "," + this.getStatusIcon()  + "," + this.getName();
    }
}
