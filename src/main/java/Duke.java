import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help?");

        Scanner sc = new Scanner(System.in);
        String[] ls = new String[100];
        int eleCount = 0;

        String command = sc.nextLine();


        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < eleCount; i++) {
                    System.out.println(i + ". " + ls[i]);
                }
            }
            ls[eleCount] = command;
            eleCount++;
            System.out.println("added: " + command);
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
