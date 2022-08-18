import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Duke\n" +
                "What can I do for you?"
        );
        loop(sc);
    }

    public void loop(Scanner sc) {
        String s = sc.nextLine();

        if ("bye".equals(s)) {
            System.out.println("Bye. Hope to see you again soon!");
            return;
        }

        System.out.println(s);
        loop(sc);
    }
}