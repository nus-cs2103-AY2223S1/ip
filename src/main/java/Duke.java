import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        String nextTask;
        String introduction = "Hello! I'm Jarvis \n"
                            + "What can I do for you?";
        String farewell = "Bye. Hope to see you again soon!";

        Task[] taskList = new Task[100];

        System.out.println(introduction);

        while (true) {
            nextTask = inputScanner.nextLine();

            if (nextTask.equals("bye")) {
                break;
            }

            if (nextTask.equals("list")) {
                for (int i = 0; i < taskList.length; i ++) {
                    if (taskList[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + taskList[i].toString());
                }
                continue;
            }

            if (nextTask.length() > 4 && nextTask.substring(0,4).equals("mark")) {
                int toMark = Integer.parseInt(nextTask.substring(5)) - 1;
                taskList[toMark].mark();
                String markResponse = "Nice! I've marked this task as done:\n ";
                System.out.println(markResponse + taskList[toMark].toString());
                continue;
            }

            if (nextTask.length() > 6 && nextTask.substring(0,6).equals("unmark")) {
                int toMark = Integer.parseInt(nextTask.substring(7)) - 1;
                taskList[toMark].unmark();
                String markResponse = "Ok, I've marked this task as not done yet:\n ";
                System.out.println(markResponse + taskList[toMark].toString());
                continue;
            }

            taskList[Task.count] = new Task(nextTask);

            System.out.println("added: " + nextTask);

        }

        System.out.println(farewell);


    }
}
