import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String LOGO = "Welcome to\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "      Chatbot!\n";

    private static final String INTRO = "Hello! I'm Duke\n"
            + "What can I do for you?";

    private static final String OUTRO = "Bye. Hope to see you again soon!";
    private static final String MARKED = "Nice! I've marked this task as done:%n  %s";
    private static final String UNMARKED = "OK, I've marked this task as not done yet:%n  %s";
    private static final String ADD_TASK = "Got it. I've added this task:%n"
            + "  %s%n"
            + "Now you have %d tasks in the list.";

    private DukeIO userIO;
    private Parser parser;

    private List<Task> tasks;
    private int taskCompleted;

    Duke() {
        userIO = new DukeIO();
        tasks = new ArrayList<>();
        parser = new Parser();
        userIO.printTask(LOGO, 2);
        userIO.printTask(INTRO, 3);
        taskCompleted = 0;
    }

    boolean handleInput() {
        String txt = userIO.readLine();
        ParsedData data = parser.parse(txt);
        Task task;
        switch (data.command) {
            case "bye":
                userIO.printTask(OUTRO, 3);
                return false;

            case "list":
                listAllTasks();
                return true;

            case "mark":
                task = tasks.get(Integer.parseInt(data.description) - 1);
                if (!task.isCompleted())
                    taskCompleted++;

                task.mark();
                userIO.printTask(String.format(MARKED, task));
                return true;

            case "unmark":
                task = tasks.get(Integer.parseInt(data.description) - 1);
                if (task.isCompleted())
                    taskCompleted--;

                task.unmark();
                userIO.printTask(String.format(UNMARKED, task));
                return true;

            case "todo":
                task = Todo.createTodo(data);
                tasks.add(task);
                userIO.printTask(String.format(ADD_TASK, task, tasks.size()));
                return true;

            case "deadline":
                task = Deadline.createDeadline(data);
                tasks.add(task);
                userIO.printTask(String.format(ADD_TASK, task, tasks.size()));
                return true;

            case "event":
                task = Event.createEvent(data);
                tasks.add(task);
                userIO.printTask(String.format(ADD_TASK, task, tasks.size()));
                return true;

            default:
                userIO.printTask("ERROR");
                return true;

        }
    }

    void listAllTasks() {
        userIO.printLine();
        for (int i = 0; i < tasks.size(); ++i) {
            userIO.printTask(String.format("%d. %s", i + 1, tasks.get(i)), 2);
        }
        userIO.printLine();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        while (duke.handleInput()) {
        }
    }
}
