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

    public static void markMessage(String userAction) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= tasks.size() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = tasks.get(index);
                    task.markAsDone();
                    sendMessage(" Nice! I've marked this task as done:\n" + "   " + task.toString());
                }
            }
        } catch (DukeException e) {
            sendMessage(e.getMessage());
        }
    }

    public static void unmarkMessage(String userAction) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= tasks.size() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = tasks.get(index);
                    task.markAsUndone();
                    sendMessage(" Nice! I've marked this task as undone:\n" + "   " + task.toString());
                }
            }
        } catch (DukeException e) {
            sendMessage(e.getMessage());
        }
    }

    public static void todoMessage(String userAction) throws DukeException {
        try {
            Task newTodo = new Todo(userAction);
            if (newTodo.description.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else{
                tasks.add(newTodo);
                sendMessage(" Got it. I've added this task:\n" + "   " + newTodo.toString()
                        + "\n Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            sendMessage(e.getMessage());
        }
    }

    public static void eventMessage(String userAction) throws DukeException {
        try {
            String[] eventString = userAction.split("/at ");
            if (eventString[0].equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (eventString[1].equals("")) {
                throw new DukeException("The date/time of an event cannot be empty.");
            } else {
                Task newEvent = new Event(eventString[0], eventString[1]);
                tasks.add(newEvent);
                sendMessage(" Got it. I've added this task:\n" + "   " + newEvent.toString()
                        + "\n Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            sendMessage(e.getMessage());
        }
    }

    public static void deadlineMessage(String userAction) throws DukeException {
        try {
            String[] deadlineString = userAction.split("/by ");
            if (deadlineString[0].equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (deadlineString[1].equals("")) {
                throw new DukeException("The date/time of a deadline cannot be empty.");
            } else {
                Task newDeadline = new Deadline(deadlineString[0], deadlineString[1]);
                tasks.add(newDeadline);
                sendMessage(" Got it. I've added this task:\n" + "   " + newDeadline.toString()
                        + "\n Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            sendMessage(e.getMessage());
        }
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

    public static void handleInput(String userCommand, String userAction) throws DukeException {
        try {
            if (userCommand.equals("bye")) {
                return;
            } else if (userCommand.equals("list")) {
                listMessage();
            } else if (userCommand.equals("mark")) {
                markMessage(userAction);
            } else if (userCommand.equals("unmark")) {
                unmarkMessage(userAction);
            } else if (userCommand.equals("todo")) {
                todoMessage(userAction);
            } else if (userCommand.equals("event")) {
                eventMessage(userAction);
            } else if (userCommand.equals("deadline")) {
                deadlineMessage(userAction);
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            sendMessage(e.getMessage());
        }
    }

    public static void main(String[] args) throws DukeException {
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
