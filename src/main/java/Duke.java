import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public enum CommandType {
        BYE,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        outer:
        while (true) {
            try {
                String s = in.next();
                CommandType type;
                try {
                    type = CommandType.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }
                switch (type) {
                case BYE:
                    break outer;
                case MARK: {
                    int selectedTask = in.nextInt();
                    if (selectedTask > tasks.size()) {
                        System.out.println("No such task");
                    } else {
                        Task t = tasks.get(selectedTask - 1);
                        t.markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + t);
                    }
                    break;
                }
                case UNMARK: {
                    int selectedTask = in.nextInt();
                    if (selectedTask > tasks.size()) {
                        System.out.println("No such task");
                    } else {
                        Task t = tasks.get(selectedTask - 1);
                        t.markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n" + t);
                    }
                    break;
                }
                case DELETE: {
                    int selectedTask = in.nextInt();
                    if (selectedTask > tasks.size()) {
                        System.out.println("No such task");
                    } else {
                        Task t = tasks.remove(selectedTask - 1);
                        int length = tasks.size();
                        String output = length == 1 ? " task in the list." : " tasks in the list.";
                        System.out.println("Noted. I've removed this task:\n" + t +
                                "\nNow you have " + length + output);
                    }
                    break;
                }
                case LIST: {
                    int length = tasks.size();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < length; i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                    break;
                }
                case TODO: {
                    s = in.nextLine();
                    if (s.length() < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    s = s.substring(1);
                    Task t = new Todo(s);
                    tasks.add(t);
                    int length = tasks.size();
                    String output = length == 1 ? " task in the list." : " tasks in the list.";
                    System.out.println("Got it. I've added this task:\n" + t +
                            "\nNow you have " + length + output);
                    break;
                }
                case DEADLINE: {
                    StringBuilder desc = new StringBuilder();
                    String token;
                    while (!(token = in.next()).equals("/by")) {
                        desc.append(" " + token);
                    }
                    s = in.nextLine();
                    s = s.substring(1);
                    Task t = new Deadline(desc.toString().substring(1), s);
                    tasks.add(t);
                    int length = tasks.size();
                    String output = length == 1 ? " task in the list." : " tasks in the list.";
                    System.out.println("Got it. I've added this task:\n" + t +
                            "\nNow you have " + length + output);
                    break;
                }
                case EVENT: {
                    StringBuilder desc = new StringBuilder();
                    String token;
                    while (!(token = in.next()).equals("/at")) {
                        desc.append(" " + token);
                    }
                    s = in.nextLine();
                    s = s.substring(1);
                    Task t = new Event(desc.toString().substring(1), s);
                    tasks.add(t);
                    int length = tasks.size();
                    String output = length == 1 ? " task in the list." : " tasks in the list.";
                    System.out.println("Got it. I've added this task:\n" + t +
                            "\nNow you have " + length + output);
                    break;
                }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            String dataFileName = "./data/duke.txt";
            FileSaver.newDir("./data");
            FileSaver.newFile(dataFileName);
            try {
                FileSaver.save(tasks, dataFileName);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

        in.close();
    }

    public static String scanNextLine(Scanner sc) throws DukeException {
        String s = sc.nextLine();
        if (s.length() < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return s;
    }
}
