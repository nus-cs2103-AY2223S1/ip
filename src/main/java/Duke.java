import java.util.Scanner;

public class Duke {
    final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        System.out.println("How can I help you today? :)");
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command);
            System.out.println("#########################");
            command = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
