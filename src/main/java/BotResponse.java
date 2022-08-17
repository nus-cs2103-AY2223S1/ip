public class BotResponse {
    public static void separationLine() {
        System.out.println("____________________________________________________________");
    }
    public static void welcome() {
        System.out.println("Hello, I'm Adam\n"
                        + "what can I do for you?");
        separationLine();
    }
    public static void bye() {
        System.out.println("Sorry to see you go, goodbye :(");
    }

    public static void addTaskLog(String description) {
        System.out.println("added: " + description);
        separationLine();
    }

    public static void markLog(Task task, boolean done) {
        if (done) {
            System.out.println("This task is done, goodjob! :)");
        } else {
            System.out.println("Oops, this task hasn't been done? I've updated it for you");
        }
        System.out.println(task);
        separationLine();
    }
}
