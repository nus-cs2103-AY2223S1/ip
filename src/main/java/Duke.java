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

    private static final String DELETE_TASK = "Noted. I've removed this task:%n"
            + "  %s%n"
            + "Now you have %d tasks in the list.";

    private static final String EMPTY_LIST = "The current list is empty!";

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

    boolean executeCommand(ParsedData data) throws DukeException {
        Task task;
        switch (data.command) {
            case "bye":
                userIO.printTask(OUTRO, 3);
                return false;

            case "list":
                listAllTasks();
                return true;

            case "mark":
                updateCompletionStatus(data, true);
                return true;

            case "unmark":
                updateCompletionStatus(data, false);
                return true;

            case "delete":
                deleteEntry(data);
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
                throw new UnknownCommandException();
        }
    }

    void deleteEntry(ParsedData data) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(data.description) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidValueException(data.command);
        }

        if (index >= tasks.size() || index < 0) {
            throw new OutOfBoundException();
        }

        Task task = tasks.remove(index);
        userIO.printTask(String.format(DELETE_TASK, task, tasks.size()));
    }

    void updateCompletionStatus(ParsedData data, boolean mark) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(data.description) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidValueException(data.command);
        }

        if (index >= tasks.size() || index < 0) {
            throw new OutOfBoundException();
        }

        Task task = tasks.get(index);
        if (!mark) {
            if (task.isCompleted())
                taskCompleted--;

            task.unmark();
            userIO.printTask(String.format(UNMARKED, task));
            return;
        }

        if (!task.isCompleted())
            taskCompleted++;

        task.mark();
        userIO.printTask(String.format(MARKED, task));
    }

    boolean handleInput() {
        String txt = userIO.readLine();
        ParsedData data = parser.parse(txt);
        boolean continueProgram = false;
        try {
            continueProgram |= executeCommand(data);
        } catch (DukeException e) {
            continueProgram = true;
            userIO.printError(e);
        }

        return continueProgram;
    }

    void listAllTasks() {
        if (tasks.size() == 0) {
            userIO.printTask(EMPTY_LIST);
            return;
        }
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
