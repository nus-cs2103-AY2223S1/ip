public class Deadlines extends Task {

    private String deadline;

    public Deadlines(String[] input) {
        super(input[0]);
        this.deadline = input[1];

    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",this.getDone() ? "X" : " ", this.getTask(), this.deadline);
    }
}
