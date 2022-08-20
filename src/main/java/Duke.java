import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREET_MESSAGE = "Hello! I am Duke. How can I help you?";

    private final TaskList tasks;
    private final Scanner scanner;

    public Duke() {
        tasks = new TaskList(Paths.get(System.getProperty("user.dir"), "data", "data.txt"));
        scanner = new Scanner(System.in);
    }

    public void run() {
        IOHelper.printWithoutPrompt(LOGO);
        IOHelper.print(GREET_MESSAGE);

        while (true) {
            try {
                Command command = Command.of(IOHelper.read(scanner));
                command.execute(tasks);

                if (command.command == CommandType.BYE) {
                    break;
                }
            } catch (DukeException e) {
                IOHelper.print(e.toString());
            }
        }
    }
}
