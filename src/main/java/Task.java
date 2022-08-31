public abstract class Task {
    boolean finished = false;
    final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setFinished() {
        this.finished = true;
    }

    public void setUnfinished() {
        this.finished = false;
    }

    public String getStatus() {
        String completed = this.finished ? "[X] " : "[ ] ";
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
            String finishedStatus = this.finished ? "finished" : "unfinished";
            return "[T], " + finishedStatus + ", " + this.taskName + "\n";
        }
    }
}
