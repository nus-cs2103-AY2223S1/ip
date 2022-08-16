import java.util.Scanner;

public class Duke {

    private static final String GREETING_MESSAGE = "Hello! I'm TedBot ヾ(≧▽≦*)o\n"
            + "What do you want to do today?";
    private static final String GOODBYE_MESSAGE = "Bye! Hope to see you soon ༼- つ ◕_◕ ༽つ";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Sorry, I don't know what that means.\n"
            + "Did you make a mistake? Please note that commands are case-sensitive.";
    private static final int TASKLIST_MAX_SIZE = 100;

    private static Task[] taskList = new Task[TASKLIST_MAX_SIZE];
    private static int taskCount = 0;

    private static void addTask(Task task) {
        assert taskCount < TASKLIST_MAX_SIZE;
        taskList[taskCount++] = task;
        System.out.printf("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", task, taskCount);
    }

    public static void main(String[] args) {

        Scanner sysIn = new Scanner(System.in);
        boolean exitCalled = false;

        System.out.println(GREETING_MESSAGE);

        while (!exitCalled) {
            /* Splits a user query into two, the command and its arguments,
               assumed to be seperated by whitespace (can be multiple characters).
             */
            String[] userQuery = sysIn.nextLine().split("\\s+", 2);
            String[] userParams;

            if (userQuery.length == 0) {
                // TODO: Add Exception when this occurs.
                System.out.println(UNKNOWN_COMMAND_MESSAGE);
                continue;
            }

            switch (userQuery[0]) {
                case "list":
                    System.out.println("Here are your tasks that I have recorded:");
                    if (taskCount == 0) {
                        System.out.println("Congratulations, you don't need to do anything right now!");
                    }
                    for (int i = 0; i < taskCount; i++) {
                        System.out.printf("%02d. %s\n", i + 1, taskList[i]);
                    }
                    break;
                case "bye":
                    System.out.println(GOODBYE_MESSAGE);
                    exitCalled = true;
                    break;
                case "add":
                    addTask(new Task(userQuery[1]));
                    break;
                case "todo":
                    addTask(new Todo(userQuery[1]));
                    break;
                case "deadline":
                    // Matches the String by the /by keyword and splits it.
                    userParams = userQuery[1].split("\\s+/by\\s+", 2);
                    // TODO: Add Exception when a parameter isn't passed
                    addTask(new Deadline(userParams[0], userParams[1]));
                    break;
                case "event":
                    // Matches the String by the /at keyword and splits it.
                    userParams = userQuery[1].split("\\s+/at\\s+", 2);
                    // TODO: Add Exception when a parameter isn't passed
                    addTask(new Event(userParams[0], userParams[1]));
                    break;
                case "mark":
                    // TODO: Check for non-integer inputs.
                    int markIndex = Integer.parseInt(userQuery[1]) - 1;
                    if (markIndex >= taskCount || markIndex < 0) {
                        System.out.println("I do not have a task with that number in my list.");
                    } else if (taskList[markIndex].getIsDone()) {
                        System.out.printf("Sorry, but it seems you have marked this task as done:\n  %s\n", taskList[markIndex]);
                    } else {
                        taskList[markIndex].setDone(true);
                        System.out.printf("Noice! I've marked this task as done:\n  %s\n", taskList[markIndex]);
                    }
                    break;
                case "unmark":
                    // TODO: Check for non-integer inputs.
                    int unmarkIndex = Integer.parseInt(userQuery[1]) - 1;
                    if (unmarkIndex >= taskCount || unmarkIndex < 0) {
                        System.out.println("I do not have a task with that number in my list.");
                    } else if (taskList[unmarkIndex].getIsDone()) {
                        taskList[unmarkIndex].setDone(false);
                        System.out.printf("Alright, I've marked this task as not done:\n  %s\n", taskList[unmarkIndex]);
                    } else {
                        System.out.printf("Sorry, but it seems you haven't marked this task as done:\n  %s\n", taskList[unmarkIndex]);
                    }
                    break;
                default:
                    System.out.println(UNKNOWN_COMMAND_MESSAGE);
            }
        }
    }
}
