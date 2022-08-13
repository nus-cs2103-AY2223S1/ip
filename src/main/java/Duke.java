import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println(">> Hello! I am Duke. How can I help you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("<< ");
            String text = sc.nextLine();

            if (text.equals("bye")) {
                System.out.println(">> Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(">> " + text);
        }
    }
}
