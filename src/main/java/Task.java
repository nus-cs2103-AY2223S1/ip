public class Task {
    private static Task[] tasks = new Task[100];
    private static int counter = 0;

    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        Task.tasks[counter] = this;
        Task.counter++;
    }

    private String getStatus() {
        return (this.done ? "X" : " ");
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    public static void markDone(int index, boolean done) {
        Task.tasks[index].done = done;
        BotResponse.markLog(Task.tasks[index], done);
    }

    public static void printTasks() {
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        BotResponse.separationLine();
    }
}
