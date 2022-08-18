import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String command;
        String list = ""; // Initial list.
        int num = 1; // Number of tasks that are created.

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        command = sc.nextLine();
        while (!command.equals("list")) {
            System.out.println("added: " + command);
            list += num + ". " + command + "\n";
            command = sc.nextLine();
            num++;
        } // Add tasks to the list and print out current task.
        System.out.println(list); // print out the list.
        command = sc.nextLine();
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } // Say goodbye.

    }



}
