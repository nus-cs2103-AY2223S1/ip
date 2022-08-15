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

    public static void main(String[] args) {

        Scanner sysIn = new Scanner(System.in);
        boolean exitCalled = false;

        System.out.println(GREETING_MESSAGE);

        while (!exitCalled) {
            /* Splits a user query into two, the command and its arguments,
               assumed to be seperated by whitespace (can be multiple characters).
             */
            String[] userQuery = sysIn.nextLine().split("\\s+", 2);

            if (userQuery.length == 0) {
                // TODO: Add Exception when this occurs.
                System.out.println(UNKNOWN_COMMAND_MESSAGE);
                continue;
            }

            switch (userQuery[0]) {
                case "list":
                    for (int i = 0; i < taskCount; i++) {
                        System.out.printf("%02d. %s\n", i + 1, taskList[i]);
                    }
                    break;
                case "bye":
                    System.out.println(GOODBYE_MESSAGE);
                    exitCalled = true;
                    break;
                case "add":
                    assert taskCount < TASKLIST_MAX_SIZE;
                    taskList[taskCount++] = new Task(userQuery[1]);
                    System.out.printf("Successfully added \"%s\" to your task list!\n", userQuery[1]);
                    break;
                default:
                    System.out.println(UNKNOWN_COMMAND_MESSAGE);
            }
        }
    }
}
