import java.util.Scanner;
public class Duke {
    public static void makeLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2015");
        }
        System.out.println();
    }

    private static void wrapAroundLines(String message) {
       makeLine();
       System.out.println(message);
       makeLine();
    }

    private static void echo(String command) {
        System.out.println(command);
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        wrapAroundLines("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        TaskList taskList = new TaskList();
        while (!command.equals("bye")) {
            String message;
            if (command.equals("list")) {
                message = taskList.toString();
            } else {
                message = taskList.addTask(command);
            }
            wrapAroundLines(message);
            command = sc.nextLine();
        }
        bye();
    }
}
