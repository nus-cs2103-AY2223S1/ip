import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \n" + "What can I do for you?\n");

        Scanner sc = new Scanner(System.in);

        List list = new List(100);

        while(true) {
            String input = sc.nextLine();
            String command = input.split(" ")[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            } else if (command.equals("list")) {
                System.out.println(list);
            } else if (command.equals("mark")) {
                int pos = Integer.parseInt(input.split(" ")[1]) - 1;
                list.markTask(pos, true);
            } else if (command.equals("unmark")) {
                int pos = Integer.parseInt(input.split(" ")[1]) - 1;
                list.markTask(pos, false);
            } else {
                System.out.println("added: " + input + "\n");
                Task task = new Task(input);
                list.addTask(task);
            }
        }
    }
}
