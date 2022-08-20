abstract class Task {
    private final String description;
    private final char symbol;
    private boolean isDone;

    private Task(String description, char symbol) {
        this.description = description;
        this.symbol = symbol;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s", this.symbol, this.isDone ? "✔" : " ", this.description);
    }

    public String toSaveString() {
        return String.format("%c | %d | %s", this.symbol, this.isDone ? 1 : 0, this.description);
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
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
            return super.toString() + " (by: " + this.deadline + ")";
        }

        @Override
        public String toSaveString() {
            return super.toSaveString() + " | " + this.deadline;
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
            return super.toString() + " (at: " + this.time + ")";
        }

        @Override
        public String toSaveString() {
            return super.toSaveString() + " | " + this.time;
        }
    }
}
