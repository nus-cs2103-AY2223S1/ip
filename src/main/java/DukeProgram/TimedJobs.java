package DukeProgram;

public class TimedJobs extends Job {
    private final String timeString;
    private final String prefix;

    public TimedJobs(String name, String timeString, String prefix) {
        super(name);
        this.timeString = timeString;
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return super.toString()
                + " (" + prefix + ": " + timeString + ")";
    }
}
