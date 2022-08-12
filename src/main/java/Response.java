public class Response {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String line = "_______________________________";

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

    public void startUp() {
        String temp = line + "\n" + logo + "\n" + "Hello! I'm Duke\nWhat can I do for you?\n" + line;
        System.out.println(temp);
    }

}
