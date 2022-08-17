import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> data = new ArrayList<>();
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
            } else if (Objects.equals(command, "list")) {
                for (int i = 0; i < data.size(); i++) {
                    System.out.println(Style.INDENTATION + (i + 1)  + ". " + data.get(i));
                }
                System.out.println("");
            } else {
                data.add(command);
                System.out.println(Style.INDENTATION + "added: " + command + '\n');
            }
        }
    }
}
