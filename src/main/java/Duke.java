import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        handleCommands();
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n"
        + "What can I do for you?\n");
    }

    private static void handleCommands() {
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String command = myObj.nextLine();
            if (Objects.equals(command, "bye")) {
                System.out.println(Style.INDENTATION + "Bye. Hope to see you again soon!\n");
                break;
            } else {
                System.out.println(Style.INDENTATION + command + "\n");
            }
        }
    }
}
