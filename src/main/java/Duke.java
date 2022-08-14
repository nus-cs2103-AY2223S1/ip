import java.util.Scanner;

public class Duke {

    private static final String GREETING_MESSAGE = "Hello! I'm TedBot ヾ(≧▽≦*)o\n"
                                    + "What do you want to do today?";
    private static final String GOODBYE_MESSAGE = "Bye! Hope to see you soon ༼- つ ◕_◕ ༽つ";

    public static void main(String[] args) {

        Scanner sysIn = new Scanner(System.in);
        boolean exitCalled = false;

        System.out.println(GREETING_MESSAGE);
        while (!exitCalled) {
            String userQuery = sysIn.nextLine();
            if (userQuery.equals("bye")) {
                System.out.println(GOODBYE_MESSAGE);
                exitCalled = true;
            } else {
                System.out.println(userQuery);
            }
        }
    }
}
