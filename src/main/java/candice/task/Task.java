package candice.task;

public abstract class Task {
    boolean isFinished = false;
    final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setFinished() {
        this.isFinished = true;
    }

    public void setUnfinished() {
        this.isFinished = false;
    }

    public String getStatus() {
        String completed = this.isFinished ? "[X] " : "[ ] ";
        return completed + this.taskName;
    }

    public abstract String getStorageDescription();

    public static class ToDo extends Task {
        public ToDo(String taskName) {
            super(taskName);
        }

        @Override
        public String getStatus() {
            return "[T]" + super.getStatus();
        }

        @Override
        public String getStorageDescription() {
            String finishedStatus = this.isFinished ? "finished" : "unfinished";
            return "[T], " + finishedStatus + ", " + this.taskName + "\n";
        }
    }
}
