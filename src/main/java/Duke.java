import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n" + logo);*/
        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        Scanner sc = new Scanner(System.in);
        String line = "---------------------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println(line);
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
                String description = input.substring(input.indexOf(" ") + 1);
                if (input.startsWith("todo")) {
                    ToDo newTodo = new ToDo(description);
                    tasks[numOfTasks] = newTodo;
                    numOfTasks++;
                } else if (input.startsWith("deadline")) {
                    Deadline newDeadline = new Deadline(description.substring(0, description.indexOf("/by") - 1),
                            description.substring(description.indexOf("/") + 4));
                    tasks[numOfTasks] = newDeadline;
                    numOfTasks++;
                } else if (input.startsWith("event")) {
                    Event newEvent = new Event(description.substring(0, description.indexOf("/at") - 1),
                            description.substring(description.indexOf("/") + 4));
                    tasks[numOfTasks] = newEvent;
                    numOfTasks++;
                }
                System.out.println("Got it. I've added this task: \n" + "  " + tasks[numOfTasks - 1].toString());
                String taskTense = numOfTasks == 1 ? " task" : " tasks";
                System.out.println("Now you have " + numOfTasks + taskTense + " in the list.");
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
