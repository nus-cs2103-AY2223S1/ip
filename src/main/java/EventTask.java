public class EventTask extends Task {
    private final String duration;
    EventTask(String task, String duration) throws EmptyTaskException, InvalidEventException {
        super(task);
        this.duration = duration;
        if (super.getName().equals("")) {
            throw new EmptyTaskException();
        }
        if (this.duration.equals("")) {
            throw new InvalidEventException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + duration + ")";
    }
}