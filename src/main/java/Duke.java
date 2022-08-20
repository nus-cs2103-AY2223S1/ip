import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    ArrayList<Task> tasks = new ArrayList<>(100);
    final String BORDER = "    ____________________________________________________________";
    final String INDENT = "     ";

    public void run() {
        welcome();
        command();
        bye();
    }

    public void msg(String content) {
        System.out.println(BORDER);
        System.out.print(content);
        System.out.println(BORDER);
        System.out.println();
    }

    public void welcome() {
        String content;
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        content = logo + "\n" + INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n";
        msg(content);
    }

    public void command() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                list();
            } else if (cmd.split(" ")[0].equals("mark")) {
                mark(cmd);
            } else if (cmd.split(" ")[0].equals("unmark")) {
                unmark(cmd);
            } else if (cmd.split(" ")[0].equals("delete")) {
                delete(cmd);
            } else {
                addTask(cmd);
            }
            cmd = sc.nextLine();
        }
        sc.close();
    }

    public void addTask(String cmd) {
        try {
            String type = cmd.split(" ")[0];
            boolean isAdded = false;
            int sizePrev = tasks.size();
            switch (type) {
            case "todo":
                addTodo(cmd);
                isAdded = sizePrev != tasks.size();
                break;

            case "deadline":
                addDeadline(cmd);
                isAdded = sizePrev != tasks.size();
                break;

            case "event":
                addEvent(cmd);
                isAdded = sizePrev != tasks.size();
                break;

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (isAdded) {
                String content;
                Task task = tasks.get(tasks.size() - 1);
                content = INDENT + "Got it. I've added this task:\n";
                content += INDENT + "  " + task + "\n";
                content += INDENT + "Now you have " + tasks.size() + " tasks in the list.\n";
                msg(content);
            }
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void addTodo(String cmd) throws DukeException {
        try {
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String desc = cmd.split(" ", 2)[1];
            Todo todo = new Todo(desc);
            tasks.add(tasks.size(), todo);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void addDeadline(String cmd) {
        try {
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (!cmd.contains("/by")) {
                throw new DukeException("☹ OOPS!!! The deadline is required. (/by)");
            }
            String[] div = cmd.split("/");
            String desc = div[0].split(" ", 2)[1];
            String by = div[1].split(" ", 2)[1];
            Deadline deadline = new Deadline(desc, by);
            tasks.add(tasks.size(), deadline);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void addEvent(String cmd) {
        try {
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (!cmd.contains("/at")) {
                throw new DukeException("☹ OOPS!!! Time of event required. (/at)");
            }
            String[] div = cmd.split("/");
            String desc = div[0].split(" ", 2)[1];
            String at = div[1].split(" ", 2)[1];
            Event event = new Event(desc, at);
            tasks.add(tasks.size(), event);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void list() {
        String content;
        content = INDENT + "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            content += INDENT + (i + 1) + "." + task + "\n";
        }
        msg(content);
    }

    public void mark(String cmd) throws DukeException {
        try {
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! This mark command is invalid.");
            }
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("☹ OOPS!!! The index is invalid.");
            }
            Task curr = tasks.get(index);
            if (curr.getStatusIcon().equals(" ")) {
                curr.markAsDone();
                msg(INDENT + "Nice! I've marked this task as done:\n" + INDENT + "  " + curr + "\n");
            } else {
                msg(INDENT + "This task was already done.\n" + INDENT + "  " + curr + "\n");
            }
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void unmark(String cmd) {
        try {
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! This mark command is invalid.");
            }
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("☹ OOPS!!! The index is invalid.");
            }
            Task curr = tasks.get(index);
            if (curr.getStatusIcon().equals("X")) {
                curr.unmarkTask();
                msg(INDENT + "OK, I've marked this task as not done yet:\n" + INDENT + "  " + curr + "\n");
            } else {
                msg(INDENT + "This task has not been done in the first place.\n" + INDENT + "  " +
                        curr + "\n");
            }
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void delete(String cmd) {
        try {
            String content;
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! This delete command is invalid.");
            }
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("☹ OOPS!!! The index is invalid.");
            }
            Task deleted = tasks.remove(index);
            content = INDENT + "Noted. I've removed this task:\n";
            content += INDENT + "  " + deleted + "\n";
            content += INDENT + "Now you have " + tasks.size() + " tasks in the list.\n";
            msg(content);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage() + "\n");
        }
    }

    public void bye() {
        msg(INDENT + "Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        Duke program = new Duke();
        program.run();
    }
}
