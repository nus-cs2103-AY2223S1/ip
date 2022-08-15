abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected char symbol;

    private Task(String description, char symbol) {
        this.description = description;
        this.symbol = symbol;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s", this.symbol, this.isDone ? "✔" : " ", this.description);
    }

    public static Task of(CommandType command, String[] args) {
        switch (command) {
        case TODO:
            return new TodoTask(args);
        case DEADLINE:
            return new DeadlineTask(args);
        case EVENT:
            return new EventTask(args);
        default:
            return null;
        }
    }

    private static class TodoTask extends Task {
        public TodoTask(String[] args) {
            super(args[0], 'T');
        }
    }

    private static class DeadlineTask extends Task {
        private final String deadline;

        public DeadlineTask(String[] args) {
            super(args[0], 'D');
            this.deadline = args[1];
        }

        @Override
        public String toString() {
            return super.toString() + " (by: " + deadline + ")";
        }
    }

    private static class EventTask extends Task {
        private final String time;

        public EventTask(String[] args) {
            super(args[0], 'E');
            this.time = args[1];
        }

        @Override
        public String toString() {
            return super.toString() + " (at: " + time + ")";
        }
    }
}
