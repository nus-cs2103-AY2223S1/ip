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
            //receive user input
            nextTask = inputScanner.nextLine();
            //check if userinput is bye, break if true
            if (nextTask.equals("bye")) {
                break;
            }
            //if userinput equals list, return task list
            if (nextTask.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < taskList.length; i ++) {
                    if (taskList[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + taskList[i].toString());
                }
                continue;
            }
            //if userinput equals mark, check which task and mark it
            if (nextTask.length() > 4 && nextTask.substring(0, 4).equals("mark")) {
                int toMark = Integer.parseInt(nextTask.substring(5)) - 1;
                taskList[toMark].mark();
                String markResponse = "Nice! I've marked this task as done:\n ";
                System.out.println(markResponse + taskList[toMark].toString());
                continue;
            }
            //if userinput equals unmark, check which task and unmark
            if (nextTask.length() > 6 && nextTask.substring(0, 6).equals("unmark")) {
                int toMark = Integer.parseInt(nextTask.substring(7)) - 1;
                taskList[toMark].unmark();
                String markResponse = "Ok, I've marked this task as not done yet:\n ";
                System.out.println(markResponse + taskList[toMark].toString());
                continue;
            }
            //if userinput equals to do add new to do task to list
            if (nextTask.length() > 4 && nextTask.substring(0, 4).equals("todo")) {
                taskList[Task.count] = new ToDo(nextTask);
                System.out.println("Got it. I've added this task:\n " + taskList[Task.count - 1]
                        + "\nNow you have " + (Task.count) + " tasks in the list.");
            }

            //if userinput equals deadline add new deadline task to list
            if (nextTask.length() > 8 && nextTask.substring(0, 8).equals("deadline")) {
                int divisor = nextTask.indexOf("/by");
                String description = nextTask.substring(9, divisor - 1);
                String date = nextTask.substring(divisor + 4);
                taskList[Task.count] = new Deadline(description, date);
                System.out.println("Got it. I've added this task:\n " + taskList[Task.count - 1]
                        + "\nNow you have " + (Task.count) + " tasks in the list.");
            }
            //if userinput equals event add new event task to list
            if (nextTask.length() > 7 && nextTask.substring(0,5).equals("event")) {
                int divisor = nextTask.indexOf("/at");
                String description = nextTask.substring(6, divisor - 1);
                String date = nextTask.substring(divisor + 4);
                taskList[Task.count] = new Event(description, date);
                System.out.println("Got it. I've added this task:\n " + taskList[Task.count - 1]
                        + "\nNow you have " + (Task.count) + " tasks in the list.");
            }
        }

        System.out.println(farewell);


    }
}
