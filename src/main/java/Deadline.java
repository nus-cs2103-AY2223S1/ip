public class Deadline extends Task {
    public String dl;

    public Deadline(String name, String dl) {
        super(name);
        this.dl = dl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + dl + ")";
    }
}
