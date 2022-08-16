import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String[] storage = new String[100];
        int ticker = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke \n What can I do for you?");
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < ticker; i++) {
                    System.out.println((i + 1) + ":" + storage[i]);
                }
            } else {
                storage[ticker] = userInput;
                ticker++;
                System.out.println("added:" + userInput);
            }
            userInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
