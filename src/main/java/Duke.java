import java.util.Scanner;

public class Duke {

    private static final String GREETING_MESSAGE = "Hello! I'm TedBot ヾ(≧▽≦*)o\n"
            + "What do you want to do today?";
    private static final String GOODBYE_MESSAGE = "Bye! Hope to see you soon ༼- つ ◕_◕ ༽つ";
    private static final int TASKLIST_MAX_SIZE = 100;

    private static Task[] taskList = new Task[TASKLIST_MAX_SIZE];
    private static int taskCount = 0;

    public static void main(String[] args) {

        Scanner sysIn = new Scanner(System.in);
        boolean exitCalled = false;

        System.out.println(GREETING_MESSAGE);

        while (!exitCalled) {
            String userQuery = sysIn.nextLine();
            switch (userQuery) {
                case "list":
                    for (int i = 0; i < taskCount; i++) {
                        System.out.printf("%02d. %s\n", i + 1, taskList[i]);
                    }
                    break;
                case "bye":
                    System.out.println(GOODBYE_MESSAGE);
                    exitCalled = true;
                    break;
                default:
                    assert taskCount < TASKLIST_MAX_SIZE;
                    taskList[taskCount++] = new Task(userQuery);
                    System.out.printf("Successfully added \"%s\" to your task list!\n", userQuery);
            }
        }
    }
}
