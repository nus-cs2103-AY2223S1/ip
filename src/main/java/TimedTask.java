public abstract class TimedTask extends Task {
    private final String timing;

    public TimedTask(String taskName, String timing) {
        super(taskName);
        this.timing = timing;
    }

    public static class Deadline extends TimedTask {
        public Deadline(String taskName, String timing) {
            super(taskName, timing);
        }

        @Override
        public String status() {
            return "[D]" + super.status() + "(by: " + super.timing + ")";
        }
    }

    public static class Event extends TimedTask {
        public Event(String taskName, String timing) {
            super(taskName, timing);
        }

        @Override
        public String status() {
            return "[E]" + super.status() + "(at: " + super.timing + ")";
        }
    }
}
