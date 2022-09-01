public abstract class Task {
    private final String taskName;
    private boolean isCompleted;

    public static class TodoTask extends Task {

        public TodoTask(String name) {
            super(name);
        }

        @Override
        public String toString() {
            if (isCompleted()) {
                return String.format("[T][X] %s", getTaskName());
            } else {
                return String.format("[T][ ] %s", getTaskName());
            }
        }
    }

    public static class DeadlineTask extends Task {

        private final String deadline;

        public DeadlineTask(String name, String date) {
            super(name);
            deadline = date;
        }

        @Override
        public String toString() {
            if (isCompleted()) {
                return String.format("[D][X] %s (By: %s)", getTaskName(), deadline);
            } else {
                return String.format("[D][ ] %s (By: %s)", getTaskName(), deadline);
            }
        }
    }

    public static class EventTask extends Task {

        private final String datetime;

        public EventTask(String name, String date) {
            super(name);
            datetime = date;
        }

        @Override
        public String toString() {
            if (isCompleted()) {
                return String.format("[E][X] %s (At: %s)", getTaskName(), datetime);
            } else {
                return String.format("[E][ ] %s (At: %s)", getTaskName(), datetime);
            }
        }
    }

    public Task(String name) {
        taskName = name;
        isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void markComplete() {
        isCompleted = true;
    }

    public void markIncomplete() {
        isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return String.format("[X] %s", taskName);
        } else {
            return String.format("[ ] %s", taskName);
        }
    }
}