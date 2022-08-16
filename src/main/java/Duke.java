
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
            } else if (!userReply.equals("list")) {
                taskList[indCount] = new Task(userReply);
                indCount++;
                System.out.println(buffLine + "\n" + "    added: " + userReply + "\n" + buffLine);
                userReply = sc.nextLine();
            } else {
                System.out.println(buffLine);
                for (int i = 0; i <  indCount; i++) {
                    System.out.println("    " + (i + 1) + ". " + taskList[i].getStatusIcon()
                    + taskList[i].description);
                }
                System.out.println(buffLine);
                userReply = sc.nextLine();
            }
        }

        System.out.println(buffLine + "\n" + "    Bye. Hope to see you again soon!"
                + "\n" + buffLine);
    }
}
