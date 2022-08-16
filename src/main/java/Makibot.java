import java.util.Scanner;

/**
 * MakiBot
 */
public class Makibot {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + "MAKIBOT");
        new Makibot().start();
    }

    /**
     * Start a conversation with MakiBot
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Makibot\n" +
                "What can I do for you?");
        while (true) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println(input);
        }
    }
}
