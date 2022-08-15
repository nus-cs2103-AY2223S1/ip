public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        setName(name);
        setIsDone(false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public static class Todo extends Task {
        public Todo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {
        private String time;

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
            return "[E]" + super.toString() + " (at: " + this.time + ")";
        }
    }

    public static class Deadline extends Task {
        private String time;

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
            return "[D]" + super.toString() + " (by: " + this.time + ")";
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

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.getName();
    }
}
