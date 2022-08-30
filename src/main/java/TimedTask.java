public abstract class TimedTask extends Task {
    final String timing;

    public TimedTask(String taskName, String timing) {
        super(taskName);
        this.timing = timing;
    }

    public static class Deadline extends TimedTask {
        public Deadline(String taskName, String timing) {
            super(taskName, timing);
        }

        @Override
        public String showStatus() {
            return "[D]" + super.showStatus() + " (by: " + super.timing + ")";
        }

        @Override
        public String showTaskListTextDescription() {
            String finishedStatus = super.finished ? "finished" : "unfinished";
            return "[D], " + finishedStatus + ", " + this.taskName + ", " + this.timing + "\n";
        }
    }

    public static class Event extends TimedTask {
        public Event(String taskName, String timing) {
            super(taskName, timing);
        }

        @Override
        public String showStatus() {
            return "[E]" + super.showStatus() + " (at: " + super.timing + ")";
        }

        @Override
        public String showTaskListTextDescription() {
            String finishedStatus = super.finished ? "finished" : "unfinished";
            return "[E], " + finishedStatus + ", " + this.taskName + ", " + this.timing + "\n";
        }

    }
}
