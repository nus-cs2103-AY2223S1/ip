package alpha.task;

public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String deadline, String taskType) {
        super(description, taskType);
        this.deadline = deadline;
    }

    public String getDeadline(){
        return this.deadline;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(uI.getANSI_CODE("ANSI_RED") +
                " (by: %s)", this.deadline + uI.getANSI_CODE(""));
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Deadline) {
            Deadline d = (Deadline) obj;
            return (d.getDescription().equals(this.getDescription()) && d.getStatus().equals(this.getStatus())
                    && d.taskType.equals(this.taskType) && d.getDeadline().equals(this.getDeadline()));
        }
        return false;
    }
}
