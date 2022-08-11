import java.util.Scanner;

public class Duke {
    private static String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static String EXIT_MSG = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        System.out.println(GREETING);

        while (scanner.hasNext()) {
            String nextCommand = scanner.nextLine();
            if (nextCommand.equals("bye")) {
                System.out.println(EXIT_MSG);
                scanner.close();
                break;
            }

            if (nextCommand.equals("list")) {
                storage.listTasks();
                continue;
            }

            if (nextCommand.startsWith("mark")) {
                try {
                    Integer targetIndex = Integer.parseInt(nextCommand.substring(5));
                    storage.markTaskAsDone(targetIndex);
                    continue;
                } catch (Exception e) {
                    System.out.println(e + "\nPlease input an integer");
                    continue;
                }
            }

            if (nextCommand.startsWith("unmark") || nextCommand.startsWith("Unmark")) {
                try {
                    Integer targetIndex = Integer.parseInt(nextCommand.substring(7));
                    storage.markTaskAsUnDone(targetIndex);
                    continue;
                } catch (Exception e) {
                    System.out.println(e + "\nPlease input an integer");
                    continue;
                }
            }

            // Handle list addition
            Task taskToAdd = new Task(nextCommand);
            storage.addTaskToList(taskToAdd);
        }
    }
}