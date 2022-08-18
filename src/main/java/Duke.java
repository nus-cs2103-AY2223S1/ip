import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "____________________________________________________________\n";

    public static void greeting() {
        String initMessage = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(LINE + initMessage + LINE);
    }

    public static void exit() {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(LINE + goodbyeMessage + LINE);
    }

    public static void listTasks(ArrayList<Task> tasklist) {
        int counter = 1;
        for (Task t : tasklist) {
            System.out.println(counter + "." + t.toString());
            counter++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        ArrayList<Task> list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greeting();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                break;

            } else if (input.equals("list")) {
                System.out.println(LINE + "Here are the tasks in your list: ");
                listTasks(list);
                System.out.println(LINE);

            } else if (input.contains("unmark")) {
                char taskNumber = input.charAt(7);
                int number = Character.getNumericValue(taskNumber);

                if (number <= 0 || number > count) {
                    System.out.println("Sorry, I can't mark this as undone if it does not exist :(");
                } else {
                    System.out.println(LINE + "OK, I've marked this task as not done yet:");
                    Task task = list.get(number - 1);
                    task.markAsUndone();
                    System.out.println(task.toString() + "\n" + LINE);
                }

            } else if (input.contains("mark")) {
                char taskNumber = input.charAt(5);
                int number = Character.getNumericValue(taskNumber);

                if (number <= 0 || number > count) {
                    System.out.println("Sorry, I can't mark this as done if it does not exist :(");
                } else {
                    System.out.println(LINE + "Nice! I've marked this task as done:");
                    Task task = list.get(number - 1);
                    task.markAsDone();
                    System.out.println(task.toString() + "\n" + LINE);
                }

            } else if (input.contains("todo")) {
                ToDo todo = new ToDo(input.substring(5)); // get the task
                list.add(count, todo);
                count++;
                System.out.println(LINE + "Got it. I've added this task:\n" + todo.toString());
                System.out.println("Now you have " + count + " tasks in this list.\n" + LINE);

            } else if (input.contains("deadline")) {
                String[] deadline = input.split("deadline ", 2);
                String taskBy = deadline[1];
                String[] task = taskBy.split("/by", 2); // Split task into its description and deadline
                Deadline d = new Deadline(task[0], task[1]);
                list.add(d);
                count++;
                System.out.println(LINE + "Got it. I've added this task:\n" + d.toString());
                System.out.println("Now you have " + count + " tasks in this list.\n" + LINE);

            } else if (input.contains("event")) {
                String[] event = input.split("event ", 2);
                String taskAt = event[1];
                String[] task = taskAt.split("/at", 2);// Split task into its description and timeline
                Event e = new Event(task[0], task[1]);
                list.add(e);
                count++;
                System.out.println(LINE + "Got it. I've added this task:\n" + e.toString());
                System.out.println("Now you have " + count + " tasks in this list.\n" + LINE);

            } else {
                ToDo t = new ToDo(input);
                list.add(count, t);
                count++;
                System.out.println(LINE + "Got it. I've added this task:\n" + t.toString());
                System.out.println("Now you have " + count + " tasks in this list.\n" + LINE);
            }
        }
    }
}
