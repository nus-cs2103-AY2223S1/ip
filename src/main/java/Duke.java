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

    private DukeIO userIO;
    private Parser parser;

    private List<Task> tasks;

    Duke() {
        userIO = new DukeIO();
        tasks = new ArrayList<>();
        parser = new Parser();
        userIO.printTask(LOGO, 2);
        userIO.printTask(INTRO, 3);
    }

    boolean handleInput() {
        String txt = userIO.readLine();
        ParsedData data = parser.parse(txt);
        int index;
        switch (data.command) {
            case "bye":
                userIO.printTask(OUTRO, 3);
                return false;

            case "list":
                listAllTasks();
                return true;

            case "mark":
                index = Integer.parseInt(data.description) - 1;
                tasks.get(index).mark();
                userIO.printTask(String.format(MARKED, tasks.get(index)));
                return true;

            case "unmark":
                index = Integer.parseInt(data.description) - 1;
                tasks.get(index).unmark();
                userIO.printTask(String.format(UNMARKED, tasks.get(index)));
                return true;
            default:
                tasks.add(new Task(data.raw));
                userIO.printTask(String.format("added: %s", data.raw));
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
