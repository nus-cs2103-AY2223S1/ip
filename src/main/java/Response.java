/**
 * Encapsulate the Response function of the chatbot
 *
 * @author: Jonas Png
 */
public class Response {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String line = "_______________________________";

    /**
     * Handles user's input into chatbot
     * @param input User input into chatbot
     */
    public void handleUserInput(String input) {
        if (input.equals("bye")) {
            String output = line + "\nBye. Hope to see you again soon!\n" + line;
            System.out.println(output);
            System.exit(0);
        } else {
            String output = line + "\n" + input + "\n" + line;
            System.out.println(output);
        }
    }

    /**
     * Handles what to show during start up of chatbot
     */
    public void startUp() {
        String temp = line + "\n" + logo + "\n" + "Hello! I'm Duke\nWhat can I do for you?\n" + line;
        System.out.println(temp);
    }

}
