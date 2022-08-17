import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Edric \nWhat can I do for you?");

        String[] data = new String[100];
        int dataIdx = 0;
        Scanner sc = new Scanner(System.in);
        // while loop keeps grabbing input from user, unless user inputs bye to break the loop
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                for (int i=0; i < dataIdx; i++) {
                    System.out.format("%d. %s\n", i + 1, data[i]);
                }
            }
            else {
                data[dataIdx++] = input;
                System.out.println("added: " + input);
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
