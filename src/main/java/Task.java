public abstract class Task {
    protected static String ATTRIBUTE_SEPARATOR = " | ";
    private String name;
    private boolean isDone;

    public Task(String name) {
        setName(name);
        setIsDone(false);
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public static class Todo extends Task {
        protected static final String SYMBOL = "T";

        public Todo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[" + this.SYMBOL + "]" + super.toString();
        }

        @Override
        public String toFormattedString() {
            return this.SYMBOL + this.ATTRIBUTE_SEPARATOR + convertBoolToInt(this.getIsDone()) + this.ATTRIBUTE_SEPARATOR + this.getName();
        }
    }

    public static class Event extends Task {
        private String time;
        protected static final String SYMBOL = "E";

        public Event(String name, String time) {
            super(name);
            this.time = time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return this.time;
        }

        @Override
        public String toString() {
            return "[" + this.SYMBOL + "]" + super.toString() + " (at: " + this.time + ")";
        }

        @Override
        public String toFormattedString() {
            return this.SYMBOL + " | " + convertBoolToInt(this.getIsDone()) + " | " + this.getName() + " | " + this.getTime();
        }
    }

    public static class Deadline extends Task {
        private String time;
        private static final String SYMBOL = "D";

        public Deadline(String name, String time) {
            super(name);
            this.time = time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return this.time;
        }

        @Override
        public String toString() {
            return "[" + this.SYMBOL + "]" + super.toString() + " (by: " + this.time + ")";
        }

        @Override
        public String toFormattedString() {
            return this.SYMBOL + " | " + convertBoolToInt(this.getIsDone()) + " | " + this.getName() + " | " + this.getTime();
        }

    }

    public static Todo todo(String msg) {
        return new Todo(msg);
    }

    public static Event event(String msg, String time) {
        return new Event(msg, time);
    }

    public static Deadline deadline(String msg, String time) {
        return new Deadline(msg, time);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    protected int convertBoolToInt(boolean bool)  {
        return bool ? 1 : 0;
    }

    public abstract String toFormattedString();

    public static Task parseTask(String formattedString) throws DukeException.ReadAttributeException {
        String[] attributes = formattedString.split(ATTRIBUTE_SEPARATOR);
        switch (attributes[0]){
        case Todo.SYMBOL:
            if (attributes.length < 1) {
                throw new DukeException.ReadAttributeException(
                        "Task", formattedString, "Number of attributes less than 1.");
            }
            return todo(attributes[1]);
        case Event.SYMBOL:
            if (attributes.length < 2) {
                throw new DukeException.ReadAttributeException(
                        "Task", formattedString, "Number of attributes less than 2.");
            }
            return event(attributes[1], attributes[2]);
        case Deadline.SYMBOL:
            if (attributes.length < 2) {
                throw new DukeException.ReadAttributeException(
                        "Task", formattedString, "Number of attributes less than 2.");
            }
            return deadline(attributes[1], attributes[2]);
        default:
            throw new DukeException.ReadAttributeException(
                    "Task", formattedString, "Task Symbol: [" + attributes[0] + "] is invalid.");
        }
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.getName();
    }
}
