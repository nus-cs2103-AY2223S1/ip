import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    public Duke() {
        reply("     Hello! I'm Duke\n     What can I do for you?");
    }

    public void reply(String msg) {
        String response = HORIZONTAL_LINE
                + msg + "\n"
                + HORIZONTAL_LINE;
        System.out.println(response);
    }
    public void echo() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String msg = sc.nextLine();
            if (msg.compareTo("bye") == 0) {
                reply("     Bye. Hope to see you again soon!");
                break;
            } else {
                reply("     " + msg);
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
