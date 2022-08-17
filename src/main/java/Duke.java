
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int indCount = 0;
        String buffLine = "    _____________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you?\n" + buffLine);
        String userReply = sc.nextLine();

        while (!userReply.equals("bye")) {
            if (userReply.startsWith("mark")) {
                int pos = Character.getNumericValue(userReply.charAt(5));
                taskList[pos - 1].markAsDone();
                System.out.println(buffLine + "\n" + "    Nice! I've marked this task as done: ");
                taskList[pos - 1].fullDesc();
                System.out.println(buffLine);
                userReply = sc.nextLine();
            } else if (userReply.startsWith("unmark")) {
                int pos = Character.getNumericValue(userReply.charAt(7));
                taskList[pos - 1].markAsUndone();
                System.out.println(buffLine + "\n" + "    Ok, I've marked this task as not done yet: ");
                taskList[pos - 1].fullDesc();
                System.out.println(buffLine);
                userReply = sc.nextLine();
            } else if (userReply.startsWith("todo")) {
                taskList[indCount] = new ToDos(userReply.substring(5, userReply.length()));
                System.out.println(buffLine + "\n" + "    Got it. I've added this task: ");
                taskList[indCount].fullDesc();
                System.out.println("    Now you have " + String.valueOf(indCount + 1) + " tasks in this list." +
                        "\n" + buffLine);
                indCount++;
                userReply = sc.nextLine();
            } else if (userReply.startsWith("deadline")) {
                int splitPoint = userReply.indexOf("/");
                taskList[indCount] = new Deadlines(userReply.substring(9, splitPoint - 1),
                        userReply.substring(splitPoint + 1, userReply.length()));
                System.out.println("    Got it. I've added this task: ");
                taskList[indCount].fullDesc();
                System.out.println("    Now you have " + String.valueOf(indCount + 1) + " tasks in this list." +
                        "\n" + buffLine);
                indCount++;
                userReply = sc.nextLine();
            } else if (userReply.startsWith("event")) {
                int splitPoint = userReply.indexOf("/");
                taskList[indCount] = new Events(userReply.substring(6, splitPoint - 1),
                        userReply.substring(splitPoint + 1, userReply.length()));
                System.out.println("    Got it. I've added this task: ");
                taskList[indCount].fullDesc();
                System.out.println("    Now you have " + String.valueOf(indCount + 1) + " tasks in this list." +
                        "\n" + buffLine);
                indCount++;
                userReply = sc.nextLine();
            }
            else if (!userReply.equals("list")) {
                taskList[indCount] = new Task(userReply);
                System.out.println(buffLine + "\n" + "    added: " + userReply);
                System.out.println("    Now you have " + String.valueOf(indCount + 1) + " tasks in this list." +
                        "\n" + buffLine);
                indCount++;
                userReply = sc.nextLine();
            } else {
                System.out.println(buffLine);
                for (int i = 0; i <  indCount; i++) {
                    System.out.println("    " + (i + 1) + ". " + taskList[i].stringDesc());
                }
                System.out.println(buffLine);
                userReply = sc.nextLine();
            }
        }

        System.out.println(buffLine + "\n" + "    Bye. Hope to see you again soon!"
                + "\n" + buffLine);
    }
}
