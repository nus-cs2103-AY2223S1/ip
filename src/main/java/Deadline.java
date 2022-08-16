public class Deadline extends Task {
    protected String endTime = "";

    Deadline(String desc, String endTime) {
        super(desc);
        this.endTime = endTime;
    }

    Deadline(String desc, String endTime, boolean isDone) {
        super(desc, isDone);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.endTime);
    }

}
