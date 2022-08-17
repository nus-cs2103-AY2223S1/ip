import java.util.Scanner;

public class Duke {

    public static void echo() {
        Scanner myScan = new Scanner(System.in);
        String s;

        while (true) {
            s = myScan.nextLine();
            if (s.equals("bye")) {
                System.out.println("----------------------");
                System.out.println("Bye, hope to see you again!");
                System.out.println("----------------------");
                break;
            } else {
                System.out.println("----------------------");
                System.out.println(s);
                System.out.println("----------------------");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        System.out.println("----------------------");

        echo();
    }
}
