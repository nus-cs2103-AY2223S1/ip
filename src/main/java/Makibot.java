import java.util.ArrayList;
import java.util.Scanner;

/**
 * MakiBot
 */
public class Makibot {
    ArrayList<String> taskList = new ArrayList<String>();

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

        // Event loop
        while (true) {
            String input = sc.nextLine();

            // Handle special commands
            switch (input) {
                // Exit
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                // List
                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i+1 + ". " + taskList.get(i));
                    }
                    break;
                default:
                    taskList.add(input);
                    System.out.println("added: " + input);
            }

        }
    }
}
