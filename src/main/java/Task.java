public class Task {
    private Boolean isDone;
    private final String description;

    public Task(String description) {
        this.description = description;
        this.markAsNotDone();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getStatus() {
        return this.isDone;
    }

    public String printTask() {
        return String.format("[%s] %s",
                this.getStatus() ? "X" : " ",
                this.getDescription());
    }

    public static char getTaskTypeChar(String entry) {
        return entry.charAt(1);
    }

    public static boolean isMarked(String entry) {
        return entry.charAt(4) == 'X';
    }

    public static <T extends Task> Task parseEntry(String entry, char type) {
        T task = null;
        String dataStr = entry.substring(7);

        switch (type) {
            case 'T':
                task = (T) new Todo(String.format("%s%s", KeywordChecker.TASK_KEYWORD_TODO, dataStr));
                break;
            case 'D':
                String deadlineStr = dataStr.substring(0, dataStr.length() - 1);
                deadlineStr = deadlineStr.replace("(by:", "/by");
                task = (T) new Deadline(String.format("%s%s",KeywordChecker.TASK_KEYWORD_DEADLINE, deadlineStr));
                break;
            case 'E':
                String eventStr = dataStr.substring(0, dataStr.length() - 1);
                eventStr = eventStr.replace("(at:", "/at");
                task = (T) new Event(String.format("%s%s", KeywordChecker.TASK_KEYWORD_EVENT, eventStr));
                break;
        }

        if (Task.isMarked(entry)) {
            task.markAsDone();
        }

        return task;
    }
}
