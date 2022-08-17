public class Deadline extends Task {
    private String doBy;

    public Deadline(String name, String doBy) {
        super(name);
        this.doBy = doBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.doBy + ")";
    }
}
