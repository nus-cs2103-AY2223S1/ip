import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> taskList = new ArrayList<>();
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
            if (!userReply.equals("list")) {
                System.out.println(buffLine + "\n" + "    added: " + userReply + "\n" + buffLine);
                taskList.add(userReply);
                userReply = sc.nextLine();
            } else {
                System.out.println(buffLine);
                for (int i = 0; i <  taskList.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + taskList.get(i));
                }
                System.out.println(buffLine);
                userReply = sc.nextLine();
            }
        }
        System.out.println(buffLine + "\n" + "    Bye. Hope to see you again soon!"
                + "\n" + buffLine);
    }
}
