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
        String[] history = new String[100];
        int count = 0;
        Scanner sc = new Scanner(System.in);
        while (running) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println(">>> Byebye! See you again soon!");
                running = false;
            } else if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + history[i]);
                }
            } else {
                history[count] = input;
                count++;
                System.out.println("added: " + input);
            }
        }
    }
}
