import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n" + logo);*/
        ArrayList<String> strList = new ArrayList<>();
        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        Scanner sc = new Scanner(System.in);
        String line = "---------------------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println(line);
        String uncheckedBox = "[ ]";
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println(line);
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numOfTasks; i++) {
                    System.out.println((i+1) + ". " + tasks[i].toString());
                }
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(input.lastIndexOf(" ") + 1)) - 1;
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(input.lastIndexOf(" ") + 1)) - 1;
                tasks[index].markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index]);
            } else {
                Task newTask = new Task(input);
                tasks[numOfTasks] = newTask;
                numOfTasks++;
                System.out.println("added: " + input);
            }
            System.out.println(line);
            input = sc.nextLine();
        }
        sc.close();
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
