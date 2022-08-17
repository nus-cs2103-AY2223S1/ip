import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello there! I'm Duke!");
        System.out.println("How can I help you?");

        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int numTasks = 0;

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Ok, see you next time!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < numTasks; i++) {
                    System.out.printf("%d. %s%n", i + 1, tasks[i]);
                }
            } else {
                tasks[numTasks++] = input;
                System.out.println("Added: " + input);
            }
        }
    }
}
