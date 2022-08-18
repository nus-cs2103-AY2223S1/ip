import java.util.Scanner;

public class Duke {
    public static final String START = "Hey there! I'm Duke.\nWhat do you want to do today?";


    private boolean ended;
    private TaskList tasks;


    public Duke() {
        this.ended = false;
        this.tasks = new TaskList();
    }

    public static void welcome() {
        Reply.printMessage(START);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        Duke.welcome();
        while(!ended) {
            try {
                Command command = Command.of(sc.nextLine(), tasks);
                this.ended = command.run();
            } catch (DukeException e) {
                Reply.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
