import java.util.Scanner;

public class Duke {
    public Duke() {
        String msg = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(msg);
    }
    public void echo() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String msg = sc.nextLine();
            if (msg.compareTo("bye") == 0) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(msg);
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
        Duke dk = new Duke();
        dk.echo();
    }
}
