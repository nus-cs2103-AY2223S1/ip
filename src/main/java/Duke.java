import java.util.Scanner;

public class Duke {
    private static String echo() {
        Scanner userScan = new Scanner(System.in);
        String message = userScan.nextLine();
        while (!message.contentEquals("bye")) {
            System.out.println(message);
            message = userScan.nextLine();
        }
        if (message.contentEquals("bye")) {
            System.out.println("Goodbye, see you soon for your next healthy reality check!");
        }
        return message;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        TaskList tasks = new TaskList();
        System.out.println("Hello! I'm Rio, the reality check you never asked for but really need.\n" +
                "What can I help with today?\n");
        Scanner userScan = new Scanner(System.in);
        String task = userScan.nextLine();
        while (true) {
            if (task.contentEquals("bye")) {
                System.out.println("Goodbye, see you soon for your next healthy reality check!");
                break;
            } else if (task.contentEquals("list")) {
                tasks.list();
                task = userScan.nextLine();
            } else {
                tasks.addTasks(task);
                System.out.println("added: " + task);
                task = userScan.nextLine();
            }
        }
    }
}
