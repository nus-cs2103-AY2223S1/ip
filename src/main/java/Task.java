public abstract class Task {
    protected String name;
    protected boolean done;
    protected String type;

    private Task(String name) {
        this.name = name;
        this.done = false;
    }

    private static class ToDo extends Task {
        private ToDo(String name) {
            super(name);
            this.type = "[T]";
        }
    }

    private static class Deadline extends Task {
        private final String dueDate;
        private Deadline(String name, String dueDate) {
            super(name);
            this.dueDate = "(" + dueDate + ")";
            this.type = "[D]";
        }

        @Override
        public String toString() {
            return super.toString() + this.dueDate;
        }
    }
    private static class Event extends Task {
        private final String date;
        private Event(String name, String date) {
            super(name);
            this.date = "(" + date +")";
            this.type = "[E]";
        }

        @Override
        public String toString() {
            return super.toString() + this.date;
        }
    }

    @Override
    public String toString() {
        return type + getStatus() + " " + name;
    }

    public static Task createTask(String name, String date, String task) {
        switch (task) {
            case "todo":
                return new ToDo(name);
            case "deadline":
                return new Deadline(name, date);
            default:
                return new Event(name, date);
        }
    }

    public void markDone() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public String getStatus() {
        return done ? "[✓]" : "[ ]";
    }

}
