import java.util.Scanner;

public class Duke {
    public static Scanner scanner = new Scanner(System.in);
    public static String readLine() {
        return scanner.nextLine();
    }
    public static void reply(String message) {
        System.out.print("> ");
        System.out.println(message);
    }
    public static void reply(String[] message) {
        for (int i = 0; i < message.length; ++i) {
            System.out.print(i == 0 ? "> " : "  ");
            System.out.println(message[i]);
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        while (true) {
            String line = readLine();
            if (line.equals("bye")) {
                reply("Bye. Hope to see you again soon!");
                return;
            } else {
                reply(line);
            }
        }
    }
}
