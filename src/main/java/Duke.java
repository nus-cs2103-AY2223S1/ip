import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    private final String DIR = System.getProperty("user.dir");
    private final String FILE_PATH = DIR + "/data/duke.txt";

    private ArrayList<Task> tasks = new ArrayList<>(100);
    final String BORDER = "    ____________________________________________________________";
    final String INDENT = "     ";

    public void run() throws IOException {
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

    public void loadTasks() throws IOException {
        File target = new File(FILE_PATH);
        if (target.exists()) {
            Scanner sc = new Scanner(target);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] components = line.split(Pattern.quote(" | "));
                String taskType = components[0];
                boolean isDone = components[1].equals("1");
                String desc = components[2];
                String cmd;
                switch (taskType) {
                    // TODO
                    case "T":
                        cmd = "todo " + desc;
                        addTodo(cmd);
                        break;

                    // DEADLINE
                    case "D":
                        String deadline = components[3];
                        cmd = "deadline " + desc + " /by " + deadline;
                        addDeadline(cmd);
                        break;

                    // EVENT
                    case "E":
                        String time = components[3];
                        cmd = "event " + desc + " /at " + time;
                        addEvent(cmd);
                        break;
                }
                if (isDone)
                    tasks.get(tasks.size() - 1).markAsDone();
            }
        } else {
            File parent = new File(DIR + "/data");
            boolean isFolderCreated = parent.mkdir();
            boolean isFileCreated = target.createNewFile();
        }
    }

    public void welcome() throws IOException {
        loadTasks();
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

    public void addTask(String cmd) throws DukeException {
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

    public void addDeadline(String cmd) throws DukeException {
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

    public void addEvent(String cmd) throws DukeException {
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

    public void list() throws DukeException {
        try {
            if (tasks.size() == 0) {
                throw new DukeException("☹ OOPS!!! You have no tasks in the list.\n");
            }
            String content;
            content = INDENT + "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                content += INDENT + (i + 1) + "." + task + "\n";
            }
            msg(content);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage());
        }
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

    public void unmark(String cmd) throws DukeException {
        try {
            String[] wordArr = cmd.split(" ");
            if (wordArr.length < 2) {
                throw new DukeException("☹ OOPS!!! This unmark command is invalid.");
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

    public void delete(String cmd) throws DukeException {
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

    public void storeTasks() throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        for (Task curr : tasks) {
            String status = curr.getStatusIcon().equals("X") ? "1" : "0";
            if (curr instanceof Todo) {
                writer.write("T | " + status + " | " + curr.getDescription() + "\n");
            } else if (curr instanceof Deadline) {
                writer.write("D | " + status + " | " + curr.getDescription() + " | " +
                        ((Deadline) curr).getBy() + "\n");
            } else if (curr instanceof Event) {
                writer.write("E | " + status + " | " + curr.getDescription() + " | " +
                        ((Event) curr).getAt() + "\n");
            }
        }
        writer.close();
    }

    public void bye() throws IOException {
        storeTasks();
        msg(INDENT + "Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) throws IOException {
        Duke program = new Duke();
        program.run();
    }
}
