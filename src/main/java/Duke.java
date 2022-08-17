import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help?");

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int eleCount = 0;

        String command = sc.nextLine();


        while (!command.equals("bye")) {
            String[] commandBreakdown = command.split(" ");
            if (command.equals("list")) {
                for (int i = 0; i < eleCount; i++) {
                    System.out.println((i+1) + ". " + tasks[i].toString());
                }
            } else if (commandBreakdown[0].equals("mark")) {
                int taskNo = Integer.valueOf(commandBreakdown[1])-1;
                tasks[taskNo].markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskNo]);
            } else if (commandBreakdown[0].equals("unmark")) {
                int taskNo = Integer.valueOf(commandBreakdown[1])-1;
                tasks[taskNo].markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskNo]);
            } else {
                tasks[eleCount] = new Task(command);
                eleCount++;
                System.out.println("added: " + command);
            }
            command = sc.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
