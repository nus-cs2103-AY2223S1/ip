import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        char triangle = '\u25B8';
        String start = triangle + " ";
        System.out.println(start + "Hi, I'm Duke!\n" + start + "What would you like to do today?");

        Scanner sc = new Scanner(System.in);
        while (true) {

            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(start + "Bye! I hope to see you again soon!");
                break;
            } else {
                System.out.println(start + input);
            }
        }
    }
}
