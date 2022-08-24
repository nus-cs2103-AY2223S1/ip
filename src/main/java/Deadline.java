public class Deadline extends Task{
    String deadline;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    Deadline(String name, String deadline, boolean status) {
        super(name, status);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadline);
    }

    //Returns deadline of task
    public String getDeadline() {
        return deadline;
    }
}
