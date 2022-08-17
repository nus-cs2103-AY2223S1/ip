import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        String horizontalLine = new String(new char[50]).replace("\0", "~");
        String greeting = "Hello! I'm Duke\n" +
                "\tWhat can I duke for you?";
        ArrayList<Task> list = new ArrayList<Task>();

        System.out.println("Hello from\n" + logo);

        System.out.println("\t" + horizontalLine);
        System.out.println("\t" + greeting);
        System.out.println("\t" + horizontalLine);

        String input = sc.nextLine();

        while (!input.toLowerCase().equals("bye")) {
            System.out.println("\t" + horizontalLine);

            if (input.toLowerCase().equals("list")) {
                System.out.println("\tHere are the tasks in your list:");

                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println(String.format("\t%s.[%s] %s",
                            i + 1,
                            task.getStatusIcon(),
                            task.description));
                }
            } else if (input.toLowerCase().startsWith("mark")){
                int index = Integer.parseInt(input.split(" ")[1]);
                Task task = list.get(index - 1);
                task.markAsDone();

                System.out.println("\tNice! I've marked this task as done:");
                System.out.println(String.format("\t[%s] %s",
                                                task.getStatusIcon(),
                                                task.description));
            } else if (input.toLowerCase().startsWith("unmark")){
                int index = Integer.parseInt(input.split(" ")[1]);
                Task task = list.get(index - 1);
                task.markAsUndone();

                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println(String.format("\t[%s] %s",
                                                task.getStatusIcon(),
                                                task.description));
            } else {
                list.add(new Task(input));
                System.out.println("\tadded: " + input);
            }

            System.out.println("\t" + horizontalLine);

            input = sc.nextLine();
        }

        System.out.println("\t" + horizontalLine);
        System.out.println("\t" + "Bye. Hope to not see you again!");
        System.out.println("\t" + "Duke hates you.");
        System.out.println("\t" + horizontalLine);
    }
}
