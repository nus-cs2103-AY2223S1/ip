import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello I'm Duke\nWhat can I do for you");
        String item = scan.nextLine();

        while (!item.equals("bye")) {
            System.out.println(item);
            item = scan.nextLine();
        }
        System.out.println("Bye. hope to see you again soon!");
    }
}
