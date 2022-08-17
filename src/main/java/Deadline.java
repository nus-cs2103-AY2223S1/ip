public class Deadline extends Task {

    protected final String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        return String.format("[%s]%s (by: %s)", this.getType(), super.toString(), this.getTime());
    }

    public String getTime() {
        return this.time;
    }
    public String getType() {
        return "D";
    }
}