import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        // Scanner to get input
        Scanner scan = new Scanner(System.in);

        System.out.println("--------------------------------------");
        String s;

        while(true) {
            s = scan.nextLine();
            System.out.println("--------------------------------------");
            if (!s.equals("bye")) {
                System.out.println(s);
                System.out.println("--------------------------------------");
            } else {
                scan.close();
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }

    }
}
