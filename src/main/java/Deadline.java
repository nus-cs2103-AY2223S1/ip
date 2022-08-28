public class Deadline extends Task {
    protected String endTime;

    public Deadline(String task, String endTime) {
        super(task, "deadline");
        this.endTime = DateTime.parseToString(endTime);
    }

    @Override
    public String toFileData() {
        return "D | " + super.toFileData() + "| " + this.endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (! (obj instanceof Deadline)) {
            return false;
        }
        Deadline temp = (Deadline) obj;
        if (super.equals(temp) && this.endTime.equals(temp.endTime)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), endTime);
    }
}
