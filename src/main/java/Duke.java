import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    //List of task operation commands that can be used
    public enum taskCommands {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE;
    }
    //Initialise a memo variable to track the list of tasks
    static List<Task> memo = new ArrayList<>();

    static Enum currCommand = null;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Print the starting statement
        System.out.println(dialog("Hello! I'm Duke", "What can I do for you?"));

        //Initialise the scanner used.
        Scanner sc = new Scanner(System.in);

        while (true) {

            String decodedMessage = decode(sc.nextLine().trim());

            if (currCommand == null) {
                try {
                    throw new InvalidCommand("I'm sorry, but I don't know what that means :-(");
                } catch (InvalidCommand e) {
                    System.out.println(dialog(e.getMessage()));
                }
            } else if (currCommand == taskCommands.BYE) {
                System.out.println(dialog("Bye. Hope to see you again soon!"));
                sc.close();
                break;
            } else if (currCommand == taskCommands.LIST) {
                String[] strArr = new String[memo.size() + 1];
                strArr[0] = "Here are the tasks in your list:";
                for (int i = 1; i < strArr.length; i++) {
                    strArr[i] = i + ". " + memo.get(i - 1);
                }
                System.out.println(dialog(strArr));
            } else {
                //Check if empty description is being given to a command
                if (decodedMessage == null) {
                    try {
                        throw new EmptyDescription("OOPS!!! The description of a command cannot be empty.");
                    } catch (EmptyDescription e) {
                        System.out.println(dialog(e.getMessage()));
                    }
                }

                //Since description is not empty, we can proceed

                if (currCommand == taskCommands.MARK) {
                    Integer index = Integer.valueOf(decodedMessage) - 1;
                    Task selectedTask = memo.get(index);
                    selectedTask.markAsDone();
                    System.out.println(dialog("Nice! I've marked this task as done:", selectedTask.toString()));
                } else if (currCommand == taskCommands.UNMARK) {
                    Integer index = Integer.valueOf(decodedMessage) - 1;
                    Task selectedTask = memo.get(index);
                    selectedTask.unMark();
                    System.out.println(dialog("Nice! I've marked this task as not done yet:", selectedTask.toString()));
                } else if (currCommand == taskCommands.DELETE) {
                    Integer index = Integer.valueOf(decodedMessage) - 1;
                    Task selectedTask = memo.get(index);
                    memo.remove(index);
                    String reminder = String.format("Now you have %d tasks in the list.", memo.size());
                    System.out.println(dialog("Noted. I've removed this task:", selectedTask.toString(), reminder));
                } else if (currCommand == taskCommands.TODO) {
                    Task todoTask = new Todo(decodedMessage);
                    memo.add(todoTask);
                    String reminder = String.format("Now you have %d tasks in the list.", memo.size());
                    System.out.println(dialog("Got it. I've added this task:", todoTask.toString(), reminder));
                } else if (currCommand == taskCommands.DEADLINE) {
                    String[] splittedStr = decodedMessage.split("/");
                    Task deadlineTask = new Deadline(splittedStr[0], splittedStr[1].substring(3));
                    memo.add(deadlineTask);
                    String reminder = String.format("Now you have %d tasks in the list.", memo.size());
                    System.out.println(dialog("Got it. I've added this task:", deadlineTask.toString(), reminder));
                } else if (currCommand == taskCommands.EVENT) {
                    String[] splittedStr = decodedMessage.split("/");
                    Task eventTask = new Event(splittedStr[0], splittedStr[1].substring(3));
                    memo.add(eventTask);
                    String reminder = String.format("Now you have %d tasks in the list.", memo.size());
                    System.out.println(dialog("Got it. I've added this task:", eventTask.toString(), reminder));
                }
            }

            //Reset the command to null after each iteration;
            currCommand = null;
        }
    }

    //To wrap the string in a dialog frame
    public static String dialog(String ...strings) {
        StringBuilder sb = new StringBuilder();
        sb.append("  ____________________________________________________________\n");
        for (String message: strings) {
            sb.append( "   " + message + "\n");
        }
        sb.append("  ____________________________________________________________\n");
        return sb.toString();
    }

    public static String decode(String message) {
        String[] strArr = message.split(" ", 2);
        String subStr = null;

        try {
            subStr = strArr[1];
        } catch (IndexOutOfBoundsException e) {
            subStr = null;
        }

        switch (strArr[0]) {
            case "bye":
                currCommand = taskCommands.BYE;
                break;
            case "list":
                currCommand = taskCommands.LIST;
                break;
            case "todo":
                currCommand = taskCommands.TODO;
                break;
            case "deadline":
                currCommand = taskCommands.DEADLINE;
                break;
            case "event":
                currCommand = taskCommands.EVENT;
                break;
            case "mark":
                currCommand = taskCommands.MARK;
                break;
            case "unmark":
                currCommand = taskCommands.UNMARK;
                break;
            case "delete":
                currCommand = taskCommands.DELETE;
                break;
        }
        return subStr;
    }
}
