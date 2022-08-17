public class BotResponse {
    public static void separationLine() {
        System.out.println("____________________________________________________________");
    }
    public static void welcome() {
        separationLine();
        System.out.println("Hello, I'm Adam\n"
                        + "what can I do for you?");
        separationLine();
    }
    public static void bye() {
        BotResponse.separationLine();
        System.out.println("Sorry to see you go, goodbye :(");
        BotResponse.separationLine();
    }

    public static void addTaskLog(Task task) {
        separationLine();
        System.out.println("Ok, new task for you: \n"
                        + " " + task);
        System.out.print("You now have " + Task.length() + " tasks. \n");
        separationLine();
    }

    public static void removeTaskLog(Task task) {
        separationLine();
        System.out.println("Ok, I've removed this task for you: \n"
                + " " + task);
        System.out.print("You now have " + (Task.length() - 1) + " tasks. \n");
        separationLine();
    }

    public static void markLog(Task task, boolean done) {
        separationLine();
        if (done) {
            System.out.println("This task is done, goodjob! :)");
        } else {
            System.out.println("Oops, this task hasn't been done? I've updated it for you");
        }
        System.out.println(task);
        separationLine();
    }
}
