import java.util.Scanner;

public class Duke {

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
        while (!command.equals("bye")) {
            System.out.println(command);
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
