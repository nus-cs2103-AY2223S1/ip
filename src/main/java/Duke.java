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

    private DukeIO userIO;

    private static String[] COMMANDS = {
            "bye"
    };

    Duke() {
        userIO = new DukeIO();
        userIO.printTask(LOGO, 2);
        userIO.printTask(INTRO, 3);
    }

    boolean handleInput() {
        String response = userIO.readLine();
        if (response.equals("bye")) {
            return false;
        }

        userIO.printTask(response);
        return true;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        while (duke.handleInput()) {
        }
    }
}
