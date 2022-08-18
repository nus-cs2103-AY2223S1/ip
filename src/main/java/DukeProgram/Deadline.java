package DukeProgram;

public class Deadline extends TimedJobs {

    public Deadline(String name, String dueString) {
        super(name, dueString, "by");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
