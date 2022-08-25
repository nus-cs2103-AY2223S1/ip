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

        TaskList taskList = new TaskList();
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
                    taskList.mark(selectedTask);
                    break;
                }
                case UNMARK: {
                    int selectedTask = in.nextInt();
                    taskList.unmark(selectedTask);
                    break;
                }
                case DELETE: {
                    int selectedTask = in.nextInt();
                    taskList.delete(selectedTask);
                    break;
                }
                case LIST: {
                    System.out.println(taskList);
                    break;
                }
                case TODO: {
                    s = in.nextLine();
                    if (s.length() < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    s = s.substring(1);
                    Task t = new Todo(s);
                    taskList.add(t);
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
                    try {
                        Task t = new Deadline(desc.substring(1), s);
                        taskList.add(t);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
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
                    try {
                        Task t = new Event(desc.substring(1), s);
                        taskList.add(t);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
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
                FileSaver.save(taskList.getTasks(), dataFileName);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

        in.close();
    }
}
