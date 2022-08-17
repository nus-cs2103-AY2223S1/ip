import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        Scanner sc = new Scanner(System.in);

        List list = new List(100);

        while(true) {
            String input = sc.nextLine();
            String[] spacedArr = input.split(" ", 2);
            String command = spacedArr[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            } else if (command.equals("list")) {
                System.out.println(list);
            } else if (command.equals("mark")) {
                int pos = Integer.parseInt(spacedArr[1]) - 1;
                list.markTask(pos, true);
            } else if (command.equals("unmark")) {
                int pos = Integer.parseInt(spacedArr[1]) - 1;
                list.markTask(pos, false);
            } else if (command.equals("todo")) {
                Task task = new ToDoTask(spacedArr[1]);
                list.addTask(task);
            } else if (command.equals("deadline")) {
                String by = input.split(" /by ")[1];
                String description = spacedArr[1].split(" /by ")[0];
                Task task = new DeadlineTask(description, by);
                list.addTask(task);
            } else if (command.equals("event")) {
                String at = input.split(" /at ")[1];
                String description = spacedArr[1].split(" /at ")[0];
                Task task = new EventTask(description, at);
                list.addTask(task);
            }
        }
    }
}
