package dukeprogram;

public class Deadline extends DatedJob {

    public Deadline(String name, String dueString) {
        super(name, dueString, "by");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
