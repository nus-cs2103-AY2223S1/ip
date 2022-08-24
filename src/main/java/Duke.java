import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Duke {
    private static final String START = "Hey there! I'm Duke.\nWhat do you want to do today?";
    private static Path PATH = Paths.get(System.getProperty("user.dir" ), "data", "duke.txt");


    private boolean hasEnded;
    private TaskList tasks;
    private Storage storage;


    public Duke() {
        this.hasEnded = false;
        try {
            this.storage = new Storage(PATH);
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            Reply.printMessage(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public static void welcome() {
        Reply.printMessage(START);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        Duke.welcome();
        while(!hasEnded) {
            try {
                Command command = Command.of(sc.nextLine(), this.tasks);
                this.hasEnded = command.run();
                this.storage.save(this.tasks);
            } catch (DukeException e) {
                Reply.printMessage(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
