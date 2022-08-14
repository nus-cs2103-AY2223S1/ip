import java.util.Scanner;

public class Duke {

    /**
     * To greet the user
     * @return A message used to greet the user
     */
    public String greet() {
        return "Hello! I'm Duke\nWhat can I do for you? ^_^";
    }

    /**
     * To echo user's input
     * @param message A message to echo
     * @return The message user's provided
     */
    public String echo(String message) {
        return "\t> " + message;
    }

    /**
     * To exit the programme after users type "bye"
     * @return A goodbye message
     */
    public String exit() {
        return "\t> Bye. Hope to see you again soon :D";
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        System.out.println(duke.greet());
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String message = scanner.nextLine();
            if (message.equalsIgnoreCase("bye")) {
                System.out.println(duke.exit());
                scanner.close();
                run = false;
            } else {
                System.out.println(duke.echo(message));
            }
        }
    }
}
