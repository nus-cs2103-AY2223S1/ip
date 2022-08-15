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

    private DukeIO userIO;

    private static String[] COMMANDS = {
            "bye"
    };

    private String[] tasks;
    private int index;

    Duke() {
        userIO = new DukeIO();
        tasks = new String[100];
        index = 0;
        userIO.printTask(LOGO, 2);
        userIO.printTask(INTRO, 3);
    }

    boolean handleInput() {
        String response = userIO.readLine();
        if (response.equals("bye")) {
            userIO.printTask(OUTRO, 3);
            return false;
        } else if (response.equals("list")) {
            listAllTasks();
            return true;
        }

        // userIO.printTask(response);
        addTasks(response);
        return true;
    }

    void listAllTasks() {
        userIO.printLine();
        for (int i = 0; i < index; ++i) {
            userIO.printTask(String.format("%d. %s", i + 1, tasks[i]), 2);
        }
        userIO.printLine();
    }

    void addTasks(String txt) {
        tasks[index++] = txt;
        userIO.printTask(String.format("added: %s", txt));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        while (duke.handleInput()) {
        }
    }
}
