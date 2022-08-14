import java.util.Scanner;

public class Duke {
    public static String line = "    ----------------------------------------";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
        System.out.println(line);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(line);

        String current = sc.nextLine();
        while (!current.equals("bye")) {
            System.out.println(line);
            System.out.println("    " + current);
            System.out.println(line);
            current = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
