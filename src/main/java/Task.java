import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        private final LocalDateTime deadline;
        private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
        private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a, MMM d, yyyy");

        public DeadlineTask(String[] args) {
            super(args[0], 'D');
            try {
                System.out.println(args[1]);
                this.deadline = LocalDateTime.parse(args[1], inputFormatter);
            }  catch (DateTimeParseException e) {
                e.printStackTrace();
                throw new DukeException("The Date/Time was not understood");
            }
        }

        @Override
        public String toString() {
            return super.toString() + " (by: " + deadline.format(outputFormatter) + ")";
        }

        @Override
        public String toSaveString() {
            return super.toSaveString() + " | " + this.deadline;
        }
    }

    private static class EventTask extends Task {
        private final LocalDateTime time;
        private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
        private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a, MMM d, yyyy");

        public EventTask(String[] args) {
            super(args[0], 'E');
            try {
                this.time = LocalDateTime.parse(args[1], inputFormatter);
            }  catch (DateTimeParseException e) {
                throw new DukeException("The Date/Time was not understood");
            }
        }

        @Override
        public String toString() {
            return super.toString() + " (at: " + time.format(outputFormatter) + ")";
        }

        @Override
        public String toSaveString() {
            return super.toSaveString() + " | " + this.time;
        }
    }
}
