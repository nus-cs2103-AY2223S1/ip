import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    private final TaskList tasks;
    private final Scanner scanner;

    public Duke() {
        tasks = new TaskList(Paths.get(System.getProperty("user.dir"), "data", "data.txt"));
        scanner = new Scanner(System.in);
    }

    public void run() {
        UI.greet();
        while (true) {
            try {
                Command command = Command.of(UI.read(scanner));
                command.execute(tasks);

                if (command.command == CommandType.BYE) {
                    break;
                }
            } catch (DukeException e) {
                UI.print(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
