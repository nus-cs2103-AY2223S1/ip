package DukeProgram;

public class Event extends TimedJobs {

    public Event(String name, String dueString) {
        super(name, dueString, "at");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
