import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] taskArray = new Task[100];
        int taskCount = 0;


        while (true) {
            String str = sc.nextLine();
            Scanner sc2 = new Scanner(str);
            String first = sc2.next();

            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);

            } else if (str.equals("list")) {
                System.out.printf("Here are the tasks in your list:\n");
                for (int i = 0; i < taskCount; i++) {
                    System.out.printf("%d.%s\n", i+1, taskArray[i].toString());
                }

            } else if (first.equals("mark") && sc2.hasNextInt()) {
                int taskNo = sc2.nextInt();
                taskArray[taskNo].markAsDone();
                System.out.printf("Nice! I've marked this task as done: \n" +
                        "  [X] %s\n", taskArray[taskNo].description);

            } else if (first.equals("unmark") && sc2.hasNextInt()) {
                int taskNo = sc2.nextInt();
                taskArray[taskNo].markAsUndone();
                System.out.printf("OK, I've marked this task as not done yet: \n" +
                        "  [ ] %s\n", taskArray[taskNo].description);

            } else { //tasks

                if (first.equals("todo")) {
                    ToDo todo = new ToDo(sc2.nextLine());
                    taskArray[taskCount] = todo;
                    taskCount++;
                    System.out.printf("Got it. I've added this task:\n" +
                            " %s\n" +
                            "Now you have %d tasks in the list.\n", todo.toString(), taskCount);
                }

                if (first.equals("deadline")) {
                    String description = "";
                    while (!sc2.hasNext("/by")) {
                        description += sc2.next();
                    }
                    String discard = sc2.next();
                    String date = sc2.nextLine();
                    Deadline deadline = new Deadline(description, date);
                    taskArray[taskCount] = deadline;
                    taskCount++;
                    System.out.printf("Got it. I've added this task:\n" +
                            "  %s\n" +
                            "Now you have %d tasks in the list.\n", deadline.toString(), taskCount);
                }
                if (first.equals("event")) {
                    String description = "";
                    while (!sc2.hasNext("/at")) {
                        description += sc2.next() + " ";
                    }
                    String discard = sc2.next();
                    String date = sc2.nextLine();
                    Event event = new Event(description, date);
                    taskArray[taskCount] = event;
                    taskCount++;
                    System.out.printf("Got it. I've added this task:\n" +
                            "  %s\n" +
                            "Now you have %d tasks in the list.\n", event.toString(), taskCount);

                }
            }
        }
    }
}

