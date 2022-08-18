import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner sc= new Scanner(System.in);
        Task[] taskArray = new Task[100];
        int taskCount = 0;

        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);

            } else if (str.equals("list")) {
                System.out.printf("Here are the tasks in your list:\n");
                for (int i = 0; i < taskCount; i++) {
                    System.out.printf("%d.[%s] %s\n", i+1, taskArray[i].getStatusIcon(), taskArray[i].description);
                }

            } else if (str.startsWith("mark")) {
                String[] word = str.split(" ");
                int taskNo = Integer.parseInt(word[1]);
                taskArray[taskNo].markAsDone();
                System.out.printf("Nice! I've marked this task as done: \n" +
                        "  [X] %s\n", taskArray[taskNo].description);

            } else if (str.startsWith("unmark")) {
                String[] word = str.split(" ");
                int taskNo = Integer.parseInt(word[1]);
                taskArray[taskNo].markAsUndone();
                System.out.printf("OK, I've marked this task as not done yet: \n" +
                        "  [ ] %s\n", taskArray[taskNo].description);
            } else {
                Task t = new Task(str);
                taskArray[taskCount] = t;
                taskCount++;
                System.out.println("added: \n" + str);
            }
        }

    }
}

