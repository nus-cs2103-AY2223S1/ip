import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String line = "____________________________________________________________";
    private static final String startMessage = " Hello! I'm Duke\n" + " What can I do for you?";
    private static final String endMessage = " Bye. Hope to see you again soon!";
    private static final ArrayList<Task> tasks = new ArrayList<>(100);

    public static void sendMessage(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void startMessage() {
        sendMessage(startMessage);
    }

    public static void listMessage() {
        sendMessage(getList(tasks));
    }

    public static void markMessage(String userAction) {
        int index = Integer.parseInt(userAction) - 1;
        if (index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsDone();
            sendMessage(" Nice! I've marked this task as done:\n" + "   " + task.toString());
        }
    }
    public static void unmarkMessage(String userAction) {
        int index = Integer.parseInt(userAction) - 1;
        if (index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsUndone();
            sendMessage(" Nice! I've marked this task as undone:\n" + "   " + task.toString());
        }
    }

    public static void todoMessage(String userAction) {
        Task newTodo = new Todo(userAction);
        tasks.add(newTodo);
        sendMessage(" Got it. I've added this task:\n" + "   " + newTodo.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void eventMessage(String userAction) {
        String[] eventString = userAction.split("/at ");
        Task newEvent = new Event(eventString[0], eventString[1]);
        tasks.add(newEvent);
        sendMessage(" Got it. I've added this task:\n" + "   " + newEvent.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void deadlineMessage(String userAction) {
        String[] deadlineString = userAction.split("/by ");
        Task newDeadline = new Deadline(deadlineString[0], deadlineString[1]);
        tasks.add(newDeadline);
        sendMessage(" Got it. I've added this task:\n" + "   " + newDeadline.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void endMessage() {
        sendMessage(endMessage);
    }

    public static String getList(ArrayList<Task> tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                list = list + " " + String.valueOf(i + 1) + ": " + tasks.get(i).toString();
                break;
            }
            list = list + " " + String.valueOf(i + 1) + ": " + tasks.get(i).toString() + "\n";
        }
        return " Here are the tasks in your list:\n" + list;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void handleInput(String userCommand, String userAction) {
        if (userCommand.equals("bye")) {
            return;
        } else if (userCommand.equals("list")) {
            listMessage();
        } else if (userCommand.equals("mark") && isNumeric(userAction)) {
            markMessage(userAction);
        } else if (userCommand.equals("unmark") && isNumeric(userAction)) {
            unmarkMessage(userAction);
        } else if (userCommand.equals("todo")) {
            todoMessage(userAction);
        } else if (userCommand.equals("event")) {
            eventMessage(userAction);
        } else if (userCommand.equals("deadline")) {
            deadlineMessage(userAction);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        startMessage();
        while (scanner.hasNextLine()) {
            String userCommand = scanner.next();
            String userAction = scanner.nextLine().stripLeading();
            handleInput(userCommand, userAction);
        }
        scanner.close();
        endMessage();
    }

}
