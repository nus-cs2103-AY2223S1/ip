public class Task {
    private static Task[] tasks = new Task[100];
    private static int counter = 0;

    private String description;

    public Task(String description) {
        this.description = description;
        tasks[counter] = this;
        counter++;
    }

    public String getDescription() {
        return this.description;
    }

    public static void printTasks() {
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getDescription());
        }
        BotResponse.separationLine();
    }
}
