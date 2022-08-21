public abstract class Task {
    public enum TaskType {
        TODO("T"),
        DEADLINE("D"),
        EVENT("E");

        public final String value;

        private TaskType(String value) {
            this.value = value;
        }
    }
    private String description;
    private TaskType type;
    private boolean isDone;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

//    public String getDescription() {
//        return this.description;
//    }
//
//    public boolean isDone() {
//        return this.isDone;
//    }

    public String getTaskIcon() {
        return type.value;
    }

    public String getStatusIcon() {
        return this.isDone ? "x" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
