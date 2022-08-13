import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String line = " ____________________________________________________________";
    private static final String startMessage = "  Hello! I'm Duke\n" + "  What can I do for you?";
    private static final String endMessage = " Bye. Hope to see you again soon!";
    private static final ArrayList<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sendMessage(startMessage);
        while (scanner.hasNextLine()) {
            String userCommand = scanner.nextLine();
            // case 1 : user inputs "bye"
            if (userCommand.equals("bye")) {
                break;
            // case 2 : user inputs "list"
            } else if (userCommand.equals("list")) {
                sendMessage(getList(tasks));
            // case 3 : user inputs "mark"
            } else if (userCommand.split(" ")[0].equals("mark")) {
                String[] s = userCommand.split(" ");
                // check if input is of length 2
                if (s.length == 2) {
                    // check if task exists
                    if (tasks.get(Integer.parseInt(s[1]) - 1) != null) {
                        // get index of task to be marked done
                        int index = Integer.parseInt(s[1]) - 1;
                        // get task
                        Task task = tasks.get(index);
                        // mark task as done
                        task.markAsDone();
                        sendMessage(" Nice! I've marked this task as done:\n" + task.toString());
                    }
                }
            // case 4 : user inputs "unmarked"
            } else if (userCommand.split(" ")[0].equals("unmark")) {
                String[] s = userCommand.split(" ");
                // check if input is of length 2
                if (s.length == 2) {
                    // check if task exists
                    if (tasks.get(Integer.parseInt(s[1]) - 1) != null) {
                        // get index of task to be marked undone
                        int index = Integer.parseInt(s[1]) - 1;
                        // get task
                        Task task = tasks.get(index);
                        // mark task as undone
                        task.markAsUndone();
                        sendMessage(" OK, I've marked this task as not done yet:\n" + task.toString());
                    }
                }
            // case 5 : user inputs task to add
            } else {
                // create new Task object and add to ArrayList of tasks
                tasks.add(new Task(userCommand));
                sendMessage("added: " + userCommand);
            }
        }
        // close scanner
        scanner.close();
        // base case : send end message when while loop is broken
        sendMessage(endMessage);
    }

    public static void sendMessage(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static String getList(ArrayList<Task> tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                list = list + String.valueOf(i + 1) + ": " + tasks.get(i).toString();
                break;
            }
            list = list + String.valueOf(i + 1) + ": " + tasks.get(i).toString() + "\n";
        }
        return list;
    }
}
