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


    private DukeIO userIO;
    private TaskList tasks;

    Duke() {
        userIO = new DukeIO();
        tasks = new TaskList();
        userIO.printTask(LOGO, 2);
        userIO.printTask(INTRO, 3);
    }

    boolean executeCommand(ParsedData data) throws DukeException {
        Task task;
        switch (data.command) {
            case "bye":
                userIO.printTask(OUTRO, 3);
                return false;

            case "list":
                tasks.displayList(userIO);
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
                tasks.addEntry(task);
                userIO.printTask(String.format(ADD_TASK, task, tasks.getSize()));
                return true;

            case "deadline":
                task = Deadline.createDeadline(data);
                tasks.addEntry(task);
                userIO.printTask(String.format(ADD_TASK, task, tasks.getSize()));
                return true;

            case "event":
                task = Event.createEvent(data);
                tasks.addEntry(task);
                userIO.printTask(String.format(ADD_TASK, task, tasks.getSize()));
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

        Task task = tasks.deleteEntry(index);
        userIO.printTask(String.format(DELETE_TASK, task, tasks.getSize()));
    }

    void updateCompletionStatus(ParsedData data, boolean mark) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(data.description) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidValueException(data.command);
        }

        Task task = tasks.get(index);
        if (!mark) {
            task.unmark();
            userIO.printTask(String.format(UNMARKED, task));
            return;
        }

        task.mark();
        userIO.printTask(String.format(MARKED, task));
    }

    boolean handleInput() {
        String txt = userIO.readLine();
        ParsedData data = Parser.parse(txt);
        boolean continueProgram = false;
        try {
            continueProgram |= executeCommand(data);
        } catch (DukeException e) {
            continueProgram = true;
            userIO.printError(e);
        }

        return continueProgram;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        while (duke.handleInput()) {
        }
    }
}
