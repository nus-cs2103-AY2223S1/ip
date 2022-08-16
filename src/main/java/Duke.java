import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    //Initialise a memo variable to track the memo of tasks
    static List<Task> memo = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Print the starting statement
        System.out.println(dialog("Hello! I'm Duke" + "\n" + "   What can I do for you?"));

        //Initialise the scanner used.
        Scanner sc = new Scanner(System.in);
        //Initialise a variable to receive the text entered.
        String message;
        while (true) {
            //Update the message variable
            message = sc.nextLine();

            if (message.equals("bye")) {
                System.out.println(dialog("Bye. Hope to see you again soon!"));
                sc.close();
                break;
            } else if (message.equals("list")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < memo.size(); i++) {
                    if (i == memo.size() - 1) {
                        sb.append(i + 1 + ". " + memo.get(i));
                    } else {
                        sb.append(i + 1 + ". " + memo.get(i) + "\n" + "   ");
                    }
                }
                System.out.println(dialog(sb.toString()));
            } else {
                if (message.startsWith("mark")) {
                    Integer index = Integer.valueOf(message.substring(5)) - 1;
                    Task curr = memo.get(index);
                    curr.markAsDone();
                    System.out.println(dialog(
                            "Nice! I've marked this task as done:\n" +
                                    "   " + curr
                    ));
                } else if (message.startsWith("unmark")) {
                    Integer index = Integer.valueOf(message.substring(7)) - 1;
                    Task curr = memo.get(index);
                    curr.unMark();
                    System.out.println(dialog(
                            "OK, I've marked this task as not done yet:\n" +
                                    "   " + curr
                    ));
                } else if (message.startsWith("todo")) {
                    Task todoTask = new Todo(message.substring(5));
                    memo.add(todoTask);
                    System.out.println(dialog(
                            "Got it. I've added this task:\n       "
                                    + todoTask + "\n   " +
                                    String.format("Now you have [%d] tasks in the list.", memo.size())
                    ));
                } else if (message.startsWith("deadline")) {
                    String subString = message.substring(9);
                    String[] splittedStr = subString.split("/");

                    //TODO (Try catch)
                    //TODO (Simplify, these 3 are using repeating codes)
                    Task deadlineTask = new Deadline(splittedStr[0], splittedStr[1].substring(3));
                    memo.add(deadlineTask);
                    System.out.println(dialog(
                            "Got it. I've added this task:\n       "
                                    + deadlineTask + "\n   " +
                                    String.format("Now you have [%d] tasks in the list.", memo.size())
                    ));
                } else if (message.startsWith("event")) {
                    String subString = message.substring(6);
                    String[] splittedStr = subString.split("/");

                    Task eventTask = new Event(splittedStr[0], splittedStr[1].substring(3));
                    memo.add(eventTask);
                    System.out.println(dialog(
                            "Got it. I've added this task:\n       "
                                    + eventTask + "\n   " +
                                    String.format("Now you have [%d] tasks in the list.", memo.size())
                    ));
                }
            }

        }
    }

    //To wrap the string in a dialog frame
    public static String dialog(String message) {
        return "  ____________________________________________________________\n" +
                "   " + message + "\n" +
                "  ____________________________________________________________\n";
    }
}
