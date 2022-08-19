public class Deadline extends Task {

    private String time;

    Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        String out = "[D]";
        out += super.toString();
        out += " (by: " + this.time + ")";
        return out;
    }
}
