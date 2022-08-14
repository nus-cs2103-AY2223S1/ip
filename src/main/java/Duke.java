import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREET_MESSAGE = "Hello! I am Duke. How can I help you?";

    private final ArrayList<Task> tasks;
    private final Scanner scanner;

    public Duke() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        IOHelper.printWithoutPrompt(LOGO);
        IOHelper.print(GREET_MESSAGE);

        while (true) {
            Command command = Command.of(IOHelper.read(scanner));
            command.execute(tasks);

            if (command.type == CommandType.BYE) {
                break;
            }
        }
    }
}
