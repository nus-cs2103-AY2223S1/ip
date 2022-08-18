public class Deadline extends Task {
    String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by:%s)", this.getStatusIcon(), this.description, this.date);
    }

}
