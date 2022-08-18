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
        String input = userScan.nextLine();
        String[] keyword = input.split(" ");
        while (true) {
            if (input.contentEquals("bye")) {
                System.out.println("Goodbye, see you soon for your next healthy reality check!");
                break;
            } else if (input.contentEquals("list")) {
                tasks.list();
                input = userScan.nextLine();
                keyword = input.split(" ");
            } else if (keyword[0].contentEquals("mark")) {
                String message = tasks.markTask(Integer.parseInt(keyword[1]));
                System.out.println("Congratulations on smashing reality!");
                System.out.println(message);
                input = userScan.nextLine();
                keyword = input.split(" ");
            } else if (keyword[0].contentEquals("unmark")) {
                String message = tasks.unmarkTask(Integer.parseInt(keyword[1]));
                System.out.println("Oops reality is catching up... this is still undone: ");
                System.out.println(message);
                input = userScan.nextLine();
                keyword = input.split(" ");
            } else {
                Task task = new Task(input);
                tasks.addTasks(task);
                System.out.println("added: " + input);
                input = userScan.nextLine();
                keyword = input.split(" ");
            }
        }
    }
}
