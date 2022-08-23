import java.util.ArrayList;

public abstract class Task {
    protected static String ATTRIBUTE_SEPARATOR = "\\|";
    private String name;
    private boolean isDone;

    public Task(String name) {
        setName(name);
        setIsDone(false);
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

    protected static int convertBoolToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    protected static boolean convertIntToBool(int i) {
        if (i == 1) {
            return true;
        } else if (i == 0) {
            return false;
        } else {
            throw new DukeException.RuntimeException(i + " is not defined when converting int to bool.");
        }
    }

    public static Task parseTask(String formattedString) throws DukeException.ReadAttributeException {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        switch (attributes.get(0)) {
        case Todo.SYMBOL:
            return Todo.parseTodo(formattedString);
        case Event.SYMBOL:
            return Event.parseEvent(formattedString);
        case Deadline.SYMBOL:
            return Deadline.parseDeadline(formattedString);
        default:
            throw new DukeException.ReadAttributeException(
                    "Task", formattedString, "Task Symbol: [" + attributes.get(0) + "] is invalid.");
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    private void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String toFormattedString();

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.getName();
    }

    public static class Todo extends Task {
        protected static final String SYMBOL = "T";

        public Todo(String name) {
            super(name);
        }

        public static Todo parseTodo(String formattedString) {
            ArrayList<String> attributes = Parser.separateAttributes(formattedString);
            if (attributes.size() < 3) {
                throw new DukeException.ReadAttributeException("Todo", formattedString, "Number of attributes less than 3");
            }
            Todo result = todo(attributes.get(2));
            if (convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
                result.markAsDone();
            }
            return result;
        }

        @Override
        public String toString() {
            return "[" + this.SYMBOL + "]" + super.toString();
        }

        @Override
        public String toFormattedString() {
            return Parser.combineAttributes(this.SYMBOL,
                    Integer.toString(convertBoolToInt(this.getIsDone())),
                    this.getName());
        }
    }

    public static class Event extends Task {
        protected static final String SYMBOL = "E";
        private String time;

        public Event(String name, String time) {
            super(name);
            this.time = time;
        }

        public static Event parseEvent(String formattedString) {
            ArrayList<String> attributes = Parser.separateAttributes(formattedString);
            if (attributes.size() < 4) {
                throw new DukeException.ReadAttributeException("Event", formattedString, "Number of attributes less than 4");
            }
            Event result = event(attributes.get(2), attributes.get(3));
            if (convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
                result.markAsDone();
            }
            return result;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "[" + this.SYMBOL + "]" + super.toString() + " (at: " + this.time + ")";
        }

        @Override
        public String toFormattedString() {
            return Parser.combineAttributes(this.SYMBOL,
                    Integer.toString(convertBoolToInt(this.getIsDone())),
                    this.getName(),
                    this.getTime());
        }
    }

    public static class Deadline extends Task {
        private static final String SYMBOL = "D";
        private String time;

        public Deadline(String name, String time) {
            super(name);
            this.time = time;
        }

        public static Deadline parseDeadline(String formattedString) {
            ArrayList<String> attributes = Parser.separateAttributes(formattedString);
            if (attributes.size() < 4) {
                throw new DukeException.ReadAttributeException("Deadline", formattedString, "Number of attributes less than 4");
            }
            Deadline result = deadline(attributes.get(2), attributes.get(3));
            if (convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
                result.markAsDone();
            }
            return result;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "[" + this.SYMBOL + "]" + super.toString() + " (by: " + this.time + ")";
        }

        @Override
        public String toFormattedString() {
            return Parser.combineAttributes(this.SYMBOL,
                    Integer.toString(convertBoolToInt(this.getIsDone())),
                    this.getName(),
                    this.getTime());
        }
    }

}
