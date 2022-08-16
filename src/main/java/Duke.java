import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        DukeControl dc = new DukeControl();
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            dc.evaluate(input);
            System.out.println("\n");
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

        sc.close();
    }
}
