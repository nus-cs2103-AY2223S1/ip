import java.util.Scanner;

public class Duke {
    String list = ""; // Initial list.
    int num = 1; // Number of tasks that are created.

    public static void main(String[] args) {
        String command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        command = sc.nextLine();
        Duke duke = new Duke();
        while (true) {
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } // Say goodbye.
            duke.PrintCommand(command);
            command = sc.nextLine();
        }
    }

    public void PrintCommand(String command) {
        if (!command.equals("list") && !command.equals("bye")) {
            System.out.println("added: " + command);
            list += num + ". " + command + "\n";
            num++;
        } // Add tasks to the list and print out current task.
        else if (command.equals("list")) {
            System.out.println(list);
        } // print out the list.
    }

}
