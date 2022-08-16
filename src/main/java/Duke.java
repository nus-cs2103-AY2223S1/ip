import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____            _    \n"
                + "|  _ \\ _   _  __| | ___\n"
                + "| | | | | | |/ _  |/ _ \\\n"
                + "| |_| | |_| | |_| |  __/\n"
                + "|____/ \\__,_|\\__,_|\\___|\n";
        System.out.println("Hello from\n" + logo);
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        while (running) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println(">>> Byebye! See you again soon!");
                running = false;
            } else {
                System.out.println(">>> " + input);
            }
        }
    }
}
