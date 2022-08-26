public class Deadline extends Task {
    private String time;

    public Deadline(String name, String time) {
        super(name, "D");
        this.time = time;
    }

    @Override
    public String output() {
        return super.output() + this.time;
    }
}
